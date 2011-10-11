package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IRequisitoConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.AdminGatekeeper;
import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.cadastros.interfaces.SimpleCadPresenter;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.componentes.defaultbox.DefaultRichTextArea;
import com.geekvigarista.scrummanager.client.telas.componentes.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

/**
 * 
 * @author caarlos0
 * 
 */
public class AddRequisitoPresenter extends SimpleCadPresenter<AddRequisitoPresenter.AddRequisitoView, AddRequisitoPresenter.AddRequisitoProxy>//Presenter<AddRequisitoPresenter.AddRequisitoView, AddRequisitoPresenter.AddRequisitoProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addreq)
	@UseGatekeeper(AdminGatekeeper.class)
	public interface AddRequisitoProxy extends ProxyPlace<AddRequisitoPresenter>
	{
		
	}
	
	public interface AddRequisitoView extends View
	{
		void setData(List<Requisito> reqs);
		
		SingleSelectionModel<Requisito> selectionModel();
		
		IntegerBox tempoEstimado();
		
		TextBox titulo();
		
		DefaultRichTextArea descricao();
		
		ListBox prioridade();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtAvancar();
		
		HasClickHandlers getBtVoltar();
		
		HasClickHandlers getBtSalvar();
		
		Label labelHorasTotais();
		
		Button btAdd();
		
		Button btRm();
	}
	
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	private Projeto projeto;
	private Requisito requisito;
	private final IRequisitoConverter converter;
	
	@Inject
	public AddRequisitoPresenter(final EventBus eventBus, final AddRequisitoView view, final AddRequisitoProxy proxy, final DispatchAsync dispatcher,
			final IRequisitoConverter converter, final PlaceManager placeManager)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.converter = converter;
		this.placeManager = placeManager;
		for(PrioridadeRequisito p : PrioridadeRequisito.values())
		{
			getView().prioridade().addItem(p.desc(), p.name());
		}
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		final String id = request.getParameter(Parameters.projid, null);
		
		if(id == null)
		{
			placeManager.revealErrorPlace(placeManager.getCurrentPlaceRequest().toString());
			return;
		}
		
		new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			protected void callService(AsyncCallback<LoadProjetoResult> asyncCallback)
			{
				dispatcher.execute(new LoadProjetoAction(id), asyncCallback);
			}
			
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				doNovo();
			}
		}.goDefault();
		
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		registerHandler(getView().getBtSalvar().addClickHandler(salvarHandler));
		registerHandler(getView().prioridade().addKeyUpHandler(salvarHandler));
		registerHandler(getView().titulo().addKeyUpHandler(salvarHandler));
		registerHandler(getView().tempoEstimado().addKeyUpHandler(salvarHandler));
		registerHandler(getView().getBtCancelar().addClickHandler(cancelarHandler));
		registerHandler(getView().btAdd().addClickHandler(novoHandler));
		registerHandler(getView().btRm().addClickHandler(excluirHandler));
		registerHandler(getView().getBtVoltar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doVoltar();
			}
		}));
		registerHandler(getView().getBtAvancar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doAvancar();
			}
		}));
		
		registerHandler(getView().selectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				setRequisito(getView().selectionModel().getSelectedObject());
			}
		}));
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
		
		converter.updateView(requisito, getView());
		
		getView().titulo().setFocus(true);
	}
	
	@Override
	public void doExcluir()
	{
		final Requisito req = getView().selectionModel().getSelectedObject();
		if(req == null)
		{
			placeManager.revealErrorPlace(placeManager.getCurrentPlaceRequest().toString());
			return;
		}
		
		new AbstractCallback<ExcluirRequisitoResult>()
		{
			@Override
			protected void callService(AsyncCallback<ExcluirRequisitoResult> asyncCallback)
			{
				dispatcher.execute(new ExcluirRequisitoAction(req), asyncCallback);
			}
			
			@Override
			public void onSuccess(ExcluirRequisitoResult result)
			{
				if(result.getResponse())
				{
					setProjeto(getProjeto());
					doNovo();
					new MsgBox(Mensagem.get.excluidoSucesso(), false); 
				}
				else
				{
					new MsgBox(Mensagem.get.erroExcluir(), true); 
				}
			}
		}.goDefault();
		
	}
	
	@Override
	public void doNovo()
	{
		getView().selectionModel().setSelected(null, true);
		setRequisito(new Requisito());
	}
	
	@Override
	public void doCancelar()
	{
		setRequisito(getView().selectionModel().getSelectedObject());
	}
	
	public void doAvancar()
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.addstaktoproj).with(Parameters.projid, projeto != null ? projeto.getId() : "null");
		placeManager.revealPlace(pr);
	}
	
	public void doVoltar()
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.addproj).with(Parameters.projid, projeto != null ? projeto.getId() : "null");
		placeManager.revealPlace(pr);
	}
	
	@Override
	public void doSalvar()
	{
		final Requisito req = converter.convert(getRequisito(), getView());
		
		new AbstractCallback<SalvarRequisitoResult>()
		{

			@Override
			protected void callService(AsyncCallback<SalvarRequisitoResult> asyncCallback)
			{
				dispatcher.execute(new SalvarRequisitoAction(req, getProjeto()), asyncCallback);
			}
			
			@Override
			public void onSuccess(SalvarRequisitoResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					setRequisito(result.getResponse());
					setProjeto(result.getProjeto());
					
					String msg = "Requisito " + result.getResponse().getTitulo() + " salvo com sucesso";
					new MsgBox(msg, false);
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		}.goDefault();
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		getView().titulo().setFocus(true);
	}
}
