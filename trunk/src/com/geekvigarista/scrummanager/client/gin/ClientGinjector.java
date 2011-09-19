package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface ClientGinjector extends Ginjector
{
	PlaceManager getPlaceManager();
	
	EventBus getEventBus();
	
	/*
	 * Providers 
	 */
	Provider<MainPresenter> getMainPresenter();
	
	AsyncProvider<HomePresenter> getHomePresenter();
	
	AsyncProvider<AddUserPresenter> getAddUserPresenter();
}
