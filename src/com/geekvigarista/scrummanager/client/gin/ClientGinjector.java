package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.requisito.AddRequisitoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenuPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.login.LoginPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.client.telas.visao.requisito.VisualizarRequisitoPresenter;
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
	
	AsyncProvider<AddStakeholderPresenter> getAddStakeholderPresenter();
	
	AsyncProvider<AddProjetoPresenter> getAddProjetoPresenter();
	
	AsyncProvider<AddRequisitoPresenter> getAddRequisitoPresenter();
	
	AsyncProvider<VisualizarRequisitoPresenter> getVisualizarRequisitoPresenter();
	
	AsyncProvider<LoginPresenter> getLoginPresenter();
	
	AsyncProvider<MainMenuPresenter> getMainMenuPresenter();
}
