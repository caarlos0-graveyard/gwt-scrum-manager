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
	
	public static void fire(EventBus eventBus, Usuario currentUser)
	{
		eventBus.fireEvent(new LoginAuthenticateEvent(currentUser));
	}
	
	private final Usuario currentUser;
	
	public LoginAuthenticateEvent(Usuario currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Usuario getUsuario()
	{
		return currentUser;
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
