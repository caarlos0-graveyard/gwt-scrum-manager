package com.geekvigarista.scrummanager.client.telas.componentes.loading;

import java.util.Date;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStartEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStartEventHandler;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEventHandler;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;

/**
 * Classe que gerencia os eventos de ocultar/mostrar o Status.
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
	}
	
	@ProxyEvent
	@Override
	public void loadStop(LoadingStopEvent event)
	{
		System.out.println("Load stop: " + new Date().getTime());
		if(status != null && status.isVisible())
		{
			status.hide();
		}
	}
	
	@ProxyEvent
	@Override
	public void loadStart(LoadingStartEvent event)
	{
		System.out.println("Load Start: " + new Date().getTime());
		status.show();
	}
}
