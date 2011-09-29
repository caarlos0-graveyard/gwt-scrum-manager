package com.geekvigarista.scrummanager.client.gatekeeper;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.events.LoginAuthenticateEvent;
import com.geekvigarista.scrummanager.client.events.LoginAuthenticatedEventHandler;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class UsuarioLogadoGatekeeper implements Gatekeeper
{
	
	private Usuario usuario;
	private final EventBus eventBus;
	
	@Inject
	public UsuarioLogadoGatekeeper(final EventBus eventBus)
	{
		super();
		this.eventBus = eventBus;
		
		this.eventBus.addHandler(LoginAuthenticateEvent.getType(), new LoginAuthenticatedEventHandler()
		{
			
			@Override
			public void onLogin(LoginAuthenticateEvent event)
			{
				usuario = event.getUsuario();
			}
		});
	}
	
	@Override
	public boolean canReveal()
	{
		if(usuario == null || usuario.getId() == null)
		{
			Window.alert("você precisa logar primeiro, seu safadinho!");
			return false;
		}
		return true;
	}
	
	public Usuario getUsuario()
	{
		return usuario;
	}
}
