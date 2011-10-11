package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarProjetoLikeAction extends UnsecuredActionImpl<BuscarProjetoListResult>
{
	private String parametro;
	
	public BuscarProjetoLikeAction(String parametro)
	{
		super();
		this.parametro = parametro;
	}
	
	@SuppressWarnings("unused")
	private BuscarProjetoLikeAction()
	{
	}
	
	public String getParametro()
	{
		return parametro;
	}
}
