package com.geekvigarista.scrummanager.shared.commands.usuario.buscar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarUsuarioListResult implements Result {
	private List<Usuario> response;

	public BuscarUsuarioListResult(List<Usuario> response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private BuscarUsuarioListResult() {
		// serialization
	}

	public List<Usuario> getResponse() {
		return response;
	}

}
