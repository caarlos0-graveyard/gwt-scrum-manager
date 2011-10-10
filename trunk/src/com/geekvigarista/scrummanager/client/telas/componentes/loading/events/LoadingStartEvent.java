package com.geekvigarista.scrummanager.client.telas.componentes.loading.events;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.StatusPopupPanelHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Evento que ao disparado, é tratado pela {@link StatusPopupPanelHandler}, e exibe o status.
 * 
 * @author caarlos0
 */
public class LoadingStartEvent extends GwtEvent<LoadingStartEventHandler>
{
	
	private static final Type<LoadingStartEventHandler> TYPE = new Type<LoadingStartEventHandler>();
	
	public static Type<LoadingStartEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus)
	{
		eventBus.fireEvent(new LoadingStartEvent());
	}
	
	public LoadingStartEvent()
	{
		super();
	}
	
	@Override
	protected void dispatch(LoadingStartEventHandler handler)
	{
		handler.loadStart(this);
	}
	
	@Override
	public Type<LoadingStartEventHandler> getAssociatedType()
	{
		return getType();
	}
}
