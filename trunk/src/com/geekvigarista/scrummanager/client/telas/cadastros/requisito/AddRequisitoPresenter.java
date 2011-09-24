package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddRequisitoPresenter extends Presenter<AddRequisitoPresenter.AddRequisitoView, AddRequisitoPresenter.AddRequisitoProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addreq)
	public interface AddRequisitoProxy extends ProxyPlace<AddRequisitoPresenter>
	{
		
	}
	
	
	public interface AddRequisitoView extends View
	{
		SingleSelectionModel<Requisito> selectionModel();
		
		HasValue<Integer> tempoEstimado();
		
		HasValue<String> titulo();
		
		ListBox prioridade();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtSalvar();
	}
	
	private final DispatchAsync dispatcher;
	private Projeto projeto;
	private Requisito requisito;
	
	@Inject
	public AddRequisitoPresenter(final EventBus eventBus, final AddRequisitoView view, final AddRequisitoProxy proxy, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.projid, null);
		if(id == null)
			return; //FIXME tratar essa pica aqui
			
		dispatcher.execute(new LoadProjetoAction(id), new AsyncCallback<LoadProjetoResult>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				// FIXME tratar
			}
			
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				System.out.println(projeto.getNome());
			}
		});
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		
		getView().getBtSalvar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				
				System.out.println("AddRequisitoPresenter.onBind().new ClickHandler() {...}.onClick()");
				
				Requisito req = new Requisito();
				req.setPrioridade(PrioridadeRequisito.ALTA);
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
				
				dispatcher.execute(new SalvarRequisitoAction(req), new AsyncCallback<SalvarRequisitoResult>()
				{
					@Override
					public void onFailure(Throwable caught)
					{
						// TODO tratar
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(SalvarRequisitoResult result)
					{
						setRequisito(result.getResponse());
						System.out.println("LULZ");
					}
				});
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
	}

	public Requisito getRequisito()
	{
		return requisito;
	}

	public void setRequisito(Requisito requisito)
	{
		this.requisito = requisito;
	}
}
