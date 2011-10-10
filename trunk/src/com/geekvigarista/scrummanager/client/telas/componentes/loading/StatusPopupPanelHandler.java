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
public class StatusPopupPanelHandler implements IStatusPopupPanelHandler
{
	private StatusPopupPanel status;
	
	@Inject
	public StatusPopupPanelHandler(EventBus eventbus)
	{
		status = new StatusPopupPanel();
		
		eventbus.addHandler(LoadingStartEvent.getType(), new LoadingStartEventHandler()
		{
			@Override
			public void loadStart(LoadingStartEvent event)
			{
				System.out.println("StatusPopupPanelHandler.StatusPopupPanelHandler(...).new LoadingStartEventHandler() {...}.loadStart()");
				System.out.println(new Date().getTime());
				status.show();
			}
		});
		
		eventbus.addHandler(LoadingStopEvent.getType(), new LoadingStopEventHandler()
		{
			@Override
			public void loadStop(LoadingStopEvent event)
			{
				System.out.println("StatusPopupPanelHandler.StatusPopupPanelHandler(...).new LoadingStopEventHandler() {...}.loadStop()");
				System.out.println(new Date().getTime());
				if(status != null && status.isVisible())
				{
					status.hide();
				}
			}
		});
	}
}
