package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarProjetosByUsuarioAction extends UnsecuredActionImpl<BuscarProjetoListResult>
{
	private Usuario usuario;
	
	public BuscarProjetosByUsuarioAction(Usuario usuario)
	{
		super();
		this.usuario = usuario;
	}
	
	@SuppressWarnings("unused")
	private BuscarProjetosByUsuarioAction()
	{
	}
	
	public Usuario getUsuario()
	{
		return usuario;
	}
	
}
