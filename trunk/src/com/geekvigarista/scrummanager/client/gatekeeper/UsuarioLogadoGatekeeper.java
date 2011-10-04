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
import com.google.gwt.user.client.Timer;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.DispatchRequest;
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
				
				if(event.isLembrar())
				{
					final long DURATION = 1000 * 60 * 60 * 24 * 14; // duas semanas
					Date expires = new Date(System.currentTimeMillis() + DURATION);
					Cookies.setCookie("user", usuario.getLogin(), expires, null, "/", false);
					Cookies.setCookie("senha", usuario.getSenha(), expires, null, "/", false);
				}
				else
				{
					Cookies.removeCookie("user");
					Cookies.removeCookie("senha");
				}
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
			usuario = null;
			final DispatchRequest request = dispatcher.execute(new VerificaUsuarioLogadoAction(), new AbstractCallback<BuscarUsuarioObjResult>()
			{
				
				@Override
				public void onSuccess(BuscarUsuarioObjResult result)
				{
					usuario = result.getResponse();
				}
			});
			
			new Timer()
			{
				@Override
				public void run()
				{
					if(!request.isPending())
					{
						this.cancel();
					}
				}
			}.scheduleRepeating(100);
		}
		
		System.out.println("UsuarioLogadoGatekeeper.getUsuario() return " + usuario != null ? usuario.getNome() : "null");
		return usuario;
	}
}
