package com.geekvigarista.scrummanager.client.telas.componentes.loading;

import java.util.Date;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStartEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStartEventHandler;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEventHandler;
import com.google.gwt.event.shared.EventBus;

/**
 * Classe que gerencia o ato de ocultar mostrar o Status.
 * 
 * @see LoadingStartEvent
 * @see LoadingStopEvent
 * 
 * @author caarlos0
 * 
 */
public class StatusPopupPanelHandler implements IStatusPopupPanelHandler, LoadingStartEventHandler, LoadingStopEventHandler
{
	private StatusPopupPanel status;
	
	@Inject
	public StatusPopupPanelHandler(EventBus eventbus)
	{
		status = new StatusPopupPanel();
		eventbus.addHandler(LoadingStartEvent.getType(), this);
		eventbus.addHandler(LoadingStopEvent.getType(), this);
	}
	
	@Override
	public void loadStop(LoadingStopEvent event)
	{
		System.out.println("Load stop: " + new Date().getTime());
		if(status != null && status.isVisible())
		{
			status.hide();
		}
	}
	
	@Override
	public void loadStart(LoadingStartEvent event)
	{
		System.out.println("Load Start: " + new Date().getTime());
		status.show();
	}
}
