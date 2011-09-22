package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.ProjetoConverter;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddProjetoPresenter extends Presenter<AddProjetoPresenter.AddProjetoView, AddProjetoPresenter.AddProjetoProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addproj)
	public interface AddProjetoProxy extends ProxyPlace<AddProjetoPresenter>
	{
	}
	
	public interface AddProjetoView extends View
	{
		HasValue<String> getNome();
		
		HasValue<Date> getDtInicio();
		
		HasValue<Date> getDtFim();
		
		HasClickHandlers getAddRequisitos();
		
		HasClickHandlers getAddStakeholders();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtSalvar();
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	private Projeto projeto;
	
	@Inject
	public AddProjetoPresenter(final EventBus eventBus, final AddProjetoView view, final AddProjetoProxy proxy, final DispatchAsync dispatch,
			final PlaceManager placeManager)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
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
				System.out.println("AddProjetoPresenter.onBind().new ClickHandler() {...}.onClick()");
				List<Requisito> requisitos = new ArrayList<Requisito>();
				List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
				Projeto projeto = ProjetoConverter.convert(getView().getNome(), getView().getDtInicio(), getView().getDtFim(), requisitos,
						stakeholders); // TODO mehlorar conversao
				
				dispatch.execute(new SalvarProjetoAction(projeto), new AsyncCallback<SalvarProjetoResult>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						// TODO trtar
						caught.printStackTrace();
						System.out
								.println("AddProjetoPresenter.onBind().new ClickHandler() {...}.onClick(...).new AsyncCallback() {...}.onFailure()");
					}
					
					@Override
					public void onSuccess(SalvarProjetoResult result)
					{
						System.out.println("oi!");
						setProjeto(result.getResponse());
					}
				});
			}
		});
		
		getView().getAddRequisitos().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				PlaceRequest pr = new PlaceRequest(NameTokens.addreq).with(Parameters.projid, projeto != null ? projeto.getId() : "null"); // HERE
				placeManager.revealPlace(pr);
			}
		});
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
}
