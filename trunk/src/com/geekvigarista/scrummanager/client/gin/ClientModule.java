package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.place.ClientPlaceManager;
import com.geekvigarista.scrummanager.client.place.DefaultPlace;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomeView;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule
{
	
	@Override
	protected void configure()
	{
		// singletons
		install(new DefaultModule(ClientPlaceManager.class));
		
		// constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
		
		// presenters
		bindPresenter(HomePresenter.class, HomePresenter.HomeView.class, HomeView.class, HomePresenter.HomeProxy.class);
		bindPresenter(MainPresenter.class, MainPresenter.MainView.class, MainView.class, MainPresenter.MainProxy.class);
		bindPresenter(AddUserPresenter.class, AddUserPresenter.AddUserView.class, AddUserView.class,
				AddUserPresenter.AddUserProxy.class);
		
	}
}
