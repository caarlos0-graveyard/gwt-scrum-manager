package com.geekvigarista.scrummanager.shared.commands.usuario.excluir;

import com.gwtplatform.dispatch.shared.Result;

public class ExcluirUsuarioResult implements Result {
	private boolean response;

	public ExcluirUsuarioResult(boolean response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private ExcluirUsuarioResult() {
		// serialization
	}

	public boolean getResponse() {
		return response;
	}

}
