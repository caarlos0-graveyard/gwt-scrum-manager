package com.geekvigarista.scrummanager.shared.commands.requisito.excluir;

import com.gwtplatform.dispatch.shared.Result;

public class ExcluirRequisitoResult implements Result {
	private boolean response;

	public ExcluirRequisitoResult(boolean response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private ExcluirRequisitoResult() {
		// serialization
	}

	public boolean getResponse() {
		return response;
	}

}
