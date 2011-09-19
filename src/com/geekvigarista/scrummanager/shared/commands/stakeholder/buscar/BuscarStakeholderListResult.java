package com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarStakeholderListResult implements Result {
	private List<Stakeholder> response;

	public BuscarStakeholderListResult(List<Stakeholder> response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private BuscarStakeholderListResult() {
		// serialization
	}

	public List<Stakeholder> getResponse() {
		return response;
	}

}
