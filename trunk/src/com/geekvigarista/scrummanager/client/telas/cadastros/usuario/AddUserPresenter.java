package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.geekvigarista.scrummanager.client.converters.UsuarioConverter;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.CadastroUsuarioProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.CadastroUsuarioView;
import com.geekvigarista.scrummanager.shared.commands.usuario.SalvarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.SalvarUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class AddUserPresenter extends Presenter<CadastroUsuarioView, CadastroUsuarioProxy>
{
	
	@ProxyStandard
	@NameToken(NameTokens.adduser)
	public interface CadastroUsuarioProxy extends Proxy<AddUserPresenter>, Place
	{
	}
	
	public interface CadastroUsuarioView extends View
	{
		HasValue<String> getNome();
		
		HasValue<String> getLogin();
		
		HasValue<String> getSenha();
		
		HasValue<String> getConfSenha();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
		
	}
	
	private final PlaceManager placeManager;
	private DispatchAsync dispatcher;
	
	@Inject
	public AddUserPresenter(EventBus eventBus, CadastroUsuarioView view, CadastroUsuarioProxy proxy, PlaceManager placeManager,
			DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		registerHandler(getView().getBtSalvar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				salvar();
			}
		}));
	}
	
	private void salvar()
	{
		Usuario usuario = UsuarioConverter.convert(getView().getNome(), getView().getLogin(), getView().getSenha());
		dispatcher.execute(new SalvarUsuarioAction(usuario), new AsyncCallback<SalvarUsuarioResult>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("FAIL");
				caught.printStackTrace();
			}
			
			@Override
			public void onSuccess(SalvarUsuarioResult result)
			{
				Window.alert("LOL " + result.getResponse().getNome());
			}
		});
	}
	
	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
	}
	
}
