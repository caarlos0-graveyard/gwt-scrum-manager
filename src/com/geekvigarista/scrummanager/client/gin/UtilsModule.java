package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.IStatusPopupPanelHandler;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.StatusPopupPanelHandler;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Module com os utilitários do sistema :P
 */
public class UtilsModule extends AbstractGinModule
{
	
	@Override
	protected void configure()
	{
		bind(IStatusPopupPanelHandler.class).to(StatusPopupPanelHandler.class).in(Singleton.class);
	}
	
}
