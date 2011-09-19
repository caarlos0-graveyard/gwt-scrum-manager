package com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir;

import com.gwtplatform.dispatch.shared.Result;

public class ExcluirStakeholderResult implements Result {
	private boolean response;

	public ExcluirStakeholderResult(boolean response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private ExcluirStakeholderResult() {
		// serialization
	}

	public boolean getResponse() {
		return response;
	}

}
