package com.geekvigarista.scrummanager.client.events;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class LogoutEvent extends GwtEvent<LogoutEventHandler>
{
	
	private static final Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();
	
	public static Type<LogoutEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, Usuario currentUser, boolean lembrar)
	{
		eventBus.fireEvent(new LogoutEvent());
	}
	
	public LogoutEvent()
	{
	}
	
	@Override
	protected void dispatch(LogoutEventHandler handler)
	{
		handler.onLogout(this);
	}
	
	@Override
	public Type<LogoutEventHandler> getAssociatedType()
	{
		return getType();
	}
}
