package com.geekvigarista.scrummanager.client.telas.componentes.loading.events;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.StatusPopupPanelHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Evento que ao disparado, é tratado pela {@link StatusPopupPanelHandler}, e oculta o status.
 * 
 * @author caarlos0
 */
public class LoadingStopEvent extends GwtEvent<LoadingStopEventHandler>
{
	
	private static final Type<LoadingStopEventHandler> TYPE = new Type<LoadingStopEventHandler>();
	
	public static Type<LoadingStopEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus)
	{
		eventBus.fireEvent(new LoadingStopEvent());
	}
	
	public LoadingStopEvent()
	{
		super();
	}
	
	@Override
	protected void dispatch(LoadingStopEventHandler handler)
	{
		handler.loadStop(this);
	}
	
	@Override
	public Type<LoadingStopEventHandler> getAssociatedType()
	{
		return getType();
	}
}
