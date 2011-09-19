package com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarStakeholderResult implements Result {
	private Stakeholder response;

	public SalvarStakeholderResult(Stakeholder response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private SalvarStakeholderResult() {
		// serialization
	}

	public Stakeholder getResponse() {
		return response;
	}

}
