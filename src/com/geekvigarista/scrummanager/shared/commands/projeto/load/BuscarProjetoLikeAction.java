package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarProjetoLikeAction extends UnsecuredActionImpl<BuscarProjetoListResult>
{
	private String parametro;
	private Usuario usuarioLogado;
	
	public BuscarProjetoLikeAction(String parametro, Usuario usuarioLogado)
	{
		super();
		this.parametro = parametro;
		this.usuarioLogado = usuarioLogado;
	}
	
	@SuppressWarnings("unused")
	private BuscarProjetoLikeAction()
	{
	}
	
	public String getParametro()
	{
		return parametro;
	}
	
	public Usuario getUsuario()
	{
		return usuarioLogado;
	}
}
