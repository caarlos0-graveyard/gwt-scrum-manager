package com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir;

import com.gwtplatform.dispatch.shared.Result;

public class ExcluirEncaminhamentoResult implements Result {
	private boolean response;

	public ExcluirEncaminhamentoResult(boolean response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private ExcluirEncaminhamentoResult() {
		// serialization
	}

	public boolean getResponse() {
		return response;
	}

}
