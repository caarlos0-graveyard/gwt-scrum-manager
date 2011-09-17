package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.place.ClientPlaceManager;
import com.geekvigarista.scrummanager.client.place.DefaultPlace;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule
{
	
	@Override
	protected void configure()
	{
		install(new DefaultModule(ClientPlaceManager.class));
		
		bindPresenter(AddUserPresenter.class, AddUserPresenter.CadastroUsuarioView.class, AddUserView.class,
				AddUserPresenter.CadastroUsuarioProxy.class);
		
		// isso vai fazer com que toda vez q um param estiver com essa anotação, o gin automagicamente use o home como valor dele
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.adduser);
	}
}
