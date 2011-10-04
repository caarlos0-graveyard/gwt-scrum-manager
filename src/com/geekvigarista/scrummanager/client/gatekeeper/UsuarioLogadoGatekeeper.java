package com.geekvigarista.scrummanager.client.gatekeeper;

import java.util.Date;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.events.LoginAuthenticateEvent;
import com.geekvigarista.scrummanager.client.events.LoginAuthenticatedEventHandler;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.VerificaUsuarioLogadoAction;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class UsuarioLogadoGatekeeper implements Gatekeeper
{
	
	private Usuario usuario;
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	
	private static final String useridcookie = "uid";
	
	@Inject
	public UsuarioLogadoGatekeeper(final EventBus eventBus, final DispatchAsync dispatcher)
	{
		super();
		System.out.println("UsuarioLogadoGatekeeper.UsuarioLogadoGatekeeper()");
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.eventBus.addHandler(LoginAuthenticateEvent.getType(), new LoginAuthenticatedEventHandler()
		{
			@Override
			public void onLogin(LoginAuthenticateEvent event)
			{
				usuario = event.getUsuario();
				
				final long DURATION = 1000 * 60 * 60 * 24 * 14; // duas semanas
				Date expires = new Date(System.currentTimeMillis() + DURATION);
				Cookies.setCookie(useridcookie, usuario.getId(), expires, null, "/", false);
			}
		});
	}
	
	@Override
	public boolean canReveal()
	{
		if(getUsuario() == null || getUsuario().getId() == null)
		{
			return false;
		}
		return true;
	}
	
	public Usuario getUsuario()
	{
		if(usuario == null || usuario.getId() == null)
		{
			usuario = new Usuario();
//			usuario.setId(Cookies.getCookie(useridcookie));
			dispatcher.execute(new VerificaUsuarioLogadoAction(), new AbstractCallback<BuscarUsuarioObjResult>()
			{

				@Override
				public void onSuccess(BuscarUsuarioObjResult result)
				{
					usuario = result.getResponse();
					System.out.println("UsuarioLogadoGatekeeper.getUsuario().new AbstractCallback() {...}.onSuccess() " + usuario);
				}
			});
		}
		
		return usuario;
	}
}
