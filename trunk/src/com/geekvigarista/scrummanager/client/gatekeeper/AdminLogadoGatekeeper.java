package com.geekvigarista.scrummanager.client.gatekeeper;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class AdminLogadoGatekeeper implements Gatekeeper
{
	private final Usuario usuarioLogado;
	
	@Inject
	public AdminLogadoGatekeeper(Usuario usuarioLogado)
	{
		super();
		this.usuarioLogado = usuarioLogado;
	}

	@Override
	public boolean canReveal()
	{
		return false;
	}

	public Usuario getUsuarioLogado()
	{
		return usuarioLogado;
	}
}
