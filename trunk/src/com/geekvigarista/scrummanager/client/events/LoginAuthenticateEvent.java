package com.geekvigarista.scrummanager.client.events;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class LoginAuthenticateEvent extends GwtEvent<LoginAuthenticatedEventHandler>
{
	
	private static final Type<LoginAuthenticatedEventHandler> TYPE = new Type<LoginAuthenticatedEventHandler>();
	
	public static Type<LoginAuthenticatedEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, Usuario currentUser, boolean lembrar)
	{
		eventBus.fireEvent(new LoginAuthenticateEvent(currentUser, lembrar));
	}
	
	private final Usuario currentUser;
	private final boolean lembrar;
	
	public LoginAuthenticateEvent(Usuario currentUser, boolean lembrar)
	{
		this.currentUser = currentUser;
		this.lembrar = lembrar;
	}
	
	public Usuario getUsuario()
	{
		return currentUser;
	}
	
	public boolean isLembrar()
	{
		return lembrar;
	}
	
	@Override
	protected void dispatch(LoginAuthenticatedEventHandler handler)
	{
		handler.onLogin(this);
	}
	
	@Override
	public Type<LoginAuthenticatedEventHandler> getAssociatedType()
	{
		return getType();
	}
}
