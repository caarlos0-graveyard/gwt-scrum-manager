package com.geekvigarista.scrummanager.shared.commands.usuario.buscar;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarUsuarioObjResult implements Result {
	private Usuario response;

	public BuscarUsuarioObjResult(Usuario response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private BuscarUsuarioObjResult() {
		// serialization
	}

	public Usuario getResponse() {
		return response;
	}

}
