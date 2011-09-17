package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.place.ClientPlaceManager;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	ClientPlaceManager getPlaceManager();
	
	Provider<AddUserPresenter> getCadastroUsuario();
}
