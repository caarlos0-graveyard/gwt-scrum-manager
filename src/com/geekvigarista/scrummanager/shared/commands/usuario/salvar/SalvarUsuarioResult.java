package com.geekvigarista.scrummanager.shared.commands.usuario.salvar;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarUsuarioResult implements Result {
	private Usuario response;

	public SalvarUsuarioResult(Usuario response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private SalvarUsuarioResult() {
		// serialization
	}

	public Usuario getResponse() {
		return response;
	}

}
