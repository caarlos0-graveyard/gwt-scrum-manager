package com.geekvigarista.scrummanager.client;

import com.geekvigarista.scrummanager.client.gin.ClientGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWT_ScrumManager implements EntryPoint
{
	
	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);
	
	@Override
	public void onModuleLoad()
	{
		DelayedBindRegistry.bind(ginjector);
		ginjector.getPlaceManager().revealCurrentPlace();
		
		// injeta o controlador de status.
		ginjector.getStatusPopupPanel();
		RootPanel.get("carregando").setVisible(false);
	}
	
}
