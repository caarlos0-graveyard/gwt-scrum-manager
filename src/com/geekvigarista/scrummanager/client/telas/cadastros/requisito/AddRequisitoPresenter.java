package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

/**
 * 
 * @author caarlos0
 * 
 */
public class AddRequisitoPresenter extends Presenter<AddRequisitoPresenter.AddRequisitoView, AddRequisitoPresenter.AddRequisitoProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addreq)
	public interface AddRequisitoProxy extends ProxyPlace<AddRequisitoPresenter>
	{
		
	}
	
	public interface AddRequisitoView extends View
	{
		void setData(List<Requisito> reqs);
		
		SingleSelectionModel<Requisito> selectionModel();
		
		IntegerBox tempoEstimado();
		
		TextBox titulo();
		
		ListBox prioridade();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtSalvar();
		
		Label labelHorasTotais();
		
		Button btAdd();
		
		Button btRm();
	}
	
	private final DispatchAsync dispatcher;
	private Projeto projeto;
	private Requisito requisito;
	
	@Inject
	public AddRequisitoPresenter(final EventBus eventBus, final AddRequisitoView view, final AddRequisitoProxy proxy, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		for(PrioridadeRequisito p : PrioridadeRequisito.values())
		{
			getView().prioridade().addItem(p.desc(), p.name());
		}
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.projid, null);
		if(id == null)
			return; //FIXME tratar essa pica aqui
			
		dispatcher.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				doNovo();
			}
		});
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		SalvarRequisitoHandler handler = new SalvarRequisitoHandler();
		getView().getBtSalvar().addClickHandler(handler);
		getView().prioridade().addKeyUpHandler(handler);
		getView().titulo().addKeyUpHandler(handler);
		getView().tempoEstimado().addKeyUpHandler(handler);
		getView().selectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				setRequisito(getView().selectionModel().getSelectedObject());
			}
		});
		getView().btAdd().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doNovo();
			}
		});
		getView().btRm().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doExcluir();
			}
		});
		getView().getBtCancelar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doCancelar();
			}
		});
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
		getView().setData(projeto.getRequisitos());
	}
	
	public Requisito getRequisito()
	{
		return requisito;
	}
	
	public void setRequisito(Requisito requisito)
	{
		this.requisito = requisito;
		if(requisito == null)
		{
			requisito = new Requisito();
		}
		getView().titulo().setText(requisito.getTitulo());
		getView().tempoEstimado().setValue(requisito.getTempoEstimado());
		getView().prioridade().setSelectedIndex(requisito.getPrioridade().ordinal());
		
		getView().titulo().setFocus(true);
	}
	
	public void doExcluir()
	{
		final Requisito req = getView().selectionModel().getSelectedObject();
		if(req == null)
		{
			//TODO tratar
			return;
		}
		dispatcher.execute(new ExcluirRequisitoAction(req), new AbstractCallback<ExcluirRequisitoResult>()
		{
			@Override
			public void onSuccess(ExcluirRequisitoResult result)
			{
				Window.alert("WIN");
				// TODO tem que alterar pra ter no result o projeto atualizado, ou a lista de requisitos atulizada.
				getProjeto().getRequisitos().remove(req);
				setProjeto(getProjeto());
			}
		});
	}
	
	public void doNovo()
	{
		getView().selectionModel().setSelected(null, true);
		setRequisito(new Requisito());
	}
	
	public void doCancelar()
	{
		setRequisito(getView().selectionModel().getSelectedObject());
	}
	
	public void doSalvar()
	{
		Requisito req = new Requisito();
		req.setPrioridade(PrioridadeRequisito.values()[(getView().prioridade().getSelectedIndex())]);
		req.setTempoEstimado(getView().tempoEstimado().getValue());
		req.setEncaminhamentos(new ArrayList<Encaminhamento>());
		req.setTitulo(getView().titulo().getValue());
		
		if(getRequisito() != null && getRequisito().getId() != null)
		{
			req.setId(getRequisito().getId());
			req.setDataCadastro(getRequisito().getDataCadastro());
			req.setTempoTotal(getRequisito().getTempoTotal());
		}
		else
		{
			req.setDataCadastro(new Date());
			req.setTempoTotal(0);
		}
		
		dispatcher.execute(new SalvarRequisitoAction(req, getProjeto()), new AbstractCallback<SalvarRequisitoResult>()
		{
			@Override
			public void onSuccess(SalvarRequisitoResult result)
			{
				setRequisito(result.getResponse());
				setProjeto(result.getProjeto());
				System.out.println("LULZ");
			}
		});
	}
	
	private class SalvarRequisitoHandler implements ClickHandler, KeyUpHandler
	{
		
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				doSalvar();
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			doSalvar();
		}
		
	}
}
