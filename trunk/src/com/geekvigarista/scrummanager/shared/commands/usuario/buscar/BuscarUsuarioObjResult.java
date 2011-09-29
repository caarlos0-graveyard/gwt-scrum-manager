package com.geekvigarista.scrummanager.shared.commands.usuario.buscar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarUsuarioObjResult implements Result {
	private Usuario response;
	private List<String> erros;

	public BuscarUsuarioObjResult(Usuario response) {
		super();
		this.response = response;
	}
	
	public BuscarUsuarioObjResult(List<String> erros)
	{
		super();
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private BuscarUsuarioObjResult() {
		// serialization
	}

	public Usuario getResponse() {
		return response;
	}

	public List<String> getErros()
	{
		return erros;
	}

}
