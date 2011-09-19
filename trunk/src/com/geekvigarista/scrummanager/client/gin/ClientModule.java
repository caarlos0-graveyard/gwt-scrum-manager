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
		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
		bindPresenter(MainPresenter.class, MainPresenter.HomeView.class, MainView.class, MainPresenter.HomeProxy.class);
		bindPresenter(AddUserPresenter.class, AddUserPresenter.CadastroUsuarioView.class, AddUserView.class,
				AddUserPresenter.CadastroUsuarioProxy.class);
		
	}
}
