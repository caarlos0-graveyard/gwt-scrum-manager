package com.geekvigarista.scrummanager.client.telas.inicio.login;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.events.LoginAuthenticateEvent;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LoginUsuarioAction;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class LoginPresenter extends Presenter<LoginPresenter.LoginView, LoginPresenter.LoginProxy>
{
	
	/*
	 * inners 
	 */
	
	public interface LoginView extends View
	{
		TextBox login();
		
		TextBox passwd();
		
		CheckBox lembrar();
		
		HasClickHandlers btlogin();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.login)
	public interface LoginProxy extends ProxyPlace<LoginPresenter>
	{
		
	}
	
	/*
	 * atributos
	 */
	
	private final DispatchAsync dispatcher;
	private Usuario usuario;
	private final PlaceManager placeManager;
	private String caminhoAnterior;
	
	/*
	 * Construtores
	 */
	
	@Inject
	public LoginPresenter(EventBus eventBus, LoginView view, LoginProxy proxy, final DispatchAsync dispatcher, final PlaceManager placeManager)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}
	
	/*
	 * metodos
	 */
	
	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
		getView().login().setFocus(true);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String r = request.getParameter(Parameters.u, null);
		if(r != null)
		{
			caminhoAnterior = r;
		}
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		LoginHandler handler = new LoginHandler();
		getView().btlogin().addClickHandler(handler);
		getView().login().addKeyUpHandler(handler);
		getView().passwd().addKeyUpHandler(handler);
	}
	
	public void doLogin()
	{
		String login = getView().login().getValue();
		String senha = getView().passwd().getValue();
		final boolean lembrar = getView().lembrar().getValue().booleanValue();
		dispatcher.execute(new LoginUsuarioAction(login, senha), new AbstractCallback<BuscarUsuarioObjResult>()
		{
			@Override
			public void onSuccess(BuscarUsuarioObjResult result)
			{
				if(result.getResponse() != null)
				{
					setUsuario(result.getResponse());
					getEventBus().fireEvent(new LoginAuthenticateEvent(getUsuario(), lembrar));
					if(caminhoAnterior != null)
					{
						History.newItem(caminhoAnterior);
					}
					else
					{
						placeManager.revealPlace(new PlaceRequest(NameTokens.home));
					}
				}
			}
		});
	}
	
	public class LoginHandler implements ClickHandler, KeyUpHandler
	{
		
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				doLogin();
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			doLogin();
		}
		
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		getView().login().setText(Cookies.getCookie("user"));
		getView().passwd().setText(Cookies.getCookie("senha"));
		getView().lembrar().setValue(true);
	}
	
	public Usuario getUsuario()
	{
		return usuario;
	}
	
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
}
