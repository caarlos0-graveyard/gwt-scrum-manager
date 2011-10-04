package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import java.util.Date;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProjetoConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddProjetoPresenter extends Presenter<AddProjetoPresenter.AddProjetoView, AddProjetoPresenter.AddProjetoProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addproj)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface AddProjetoProxy extends ProxyPlace<AddProjetoPresenter>
	{
	}
	
	public interface AddProjetoView extends View
	{
		TextBox getNome();
		
		HasValue<Date> getDtInicio();
		
		HasValue<Date> getDtFim();
		
		//		Button getAddRequisitos();
		//		
		//		Button getAddStakeholders();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtSalvar();
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	private final IProjetoConverter converter;
	private Projeto projeto;
	private SalvarAction action;
	
	@Inject
	public AddProjetoPresenter(final EventBus eventBus, final AddProjetoView view, final AddProjetoProxy proxy, final DispatchAsync dispatch,
			final PlaceManager placeManager, final IProjetoConverter converter)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
		this.converter = converter;
		action = new SalvarAction();
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
		getView().getBtSalvar().addClickHandler(action);
		getView().getNome().addKeyUpHandler(action);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.projid, null);
		
		if(id == null)
		{
			setProjeto(null);
			return;
		}
		
		dispatch.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
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
		converter.updateView(projeto, getView());
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		getView().getNome().setFocus(true);
	}
	
	private class SalvarAction implements KeyUpHandler, ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			doSalvar();
		}
		
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
			{
				doSalvar();
			}
		}
	}
	
	public void doAvancar()
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.addreq).with(Parameters.projid, projeto != null ? projeto.getId() : "null"); // HERE
		placeManager.revealPlace(pr);
	}
	
	public void doSalvar()
	{
		Projeto projetoConvertido = converter.convert(getProjeto(), getView());
		dispatch.execute(new SalvarProjetoAction(projetoConvertido), new AbstractCallback<SalvarProjetoResult>()
		{
			@Override
			public void onSuccess(SalvarProjetoResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					setProjeto(result.getResponse());
					
					String msg = "Projeto " + result.getResponse().getNome() + " salvo com sucesso";
					new MsgBox(msg, false);
					doAvancar();
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
				
			}
		});
	}
}
