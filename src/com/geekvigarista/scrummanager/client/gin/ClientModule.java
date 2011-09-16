package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.place.ClientPlaceManager;
import com.geekvigarista.scrummanager.client.presenter.CadastroUsuarioPresenter;
import com.geekvigarista.scrummanager.client.view.CadastroUsuarioView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule
{
	
	@Override
	protected void configure()
	{
		install(new DefaultModule(ClientPlaceManager.class));
		
		bindPresenter(CadastroUsuarioPresenter.class, 
				CadastroUsuarioPresenter.CadastroUsuarioView.class, 
				CadastroUsuarioView.class,
				CadastroUsuarioPresenter.CadastroUsuarioProxy.class);
	}
}
