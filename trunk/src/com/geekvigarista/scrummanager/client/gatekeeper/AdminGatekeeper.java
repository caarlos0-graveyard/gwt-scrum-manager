package com.geekvigarista.scrummanager.client.gatekeeper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class AdminGatekeeper implements Gatekeeper
{
	
	private final UsuarioLogadoGatekeeper usuarioGatekeeper;
	
	@Inject
	public AdminGatekeeper(UsuarioLogadoGatekeeper usuarioGatekeeper)
	{
		super();
		this.usuarioGatekeeper = usuarioGatekeeper;
	}
	
	@Override
	public boolean canReveal()
	{
		return usuarioGatekeeper != null && usuarioGatekeeper.getUsuario() != null && usuarioGatekeeper.getUsuario().isAdministrador();
	}
	
}
