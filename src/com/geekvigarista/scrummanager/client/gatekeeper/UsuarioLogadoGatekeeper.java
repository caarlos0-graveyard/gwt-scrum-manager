package com.geekvigarista.scrummanager.client.gatekeeper;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.user.client.Window;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class UsuarioLogadoGatekeeper implements Gatekeeper
{
	
	private final Usuario usuario;
	
	@Inject
	public UsuarioLogadoGatekeeper(final Usuario usuario)
	{
		super();
		this.usuario = usuario;
	}
	
	@Override
	public boolean canReveal()
	{
		System.out.println(usuario != null ? "nome" : null);
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
