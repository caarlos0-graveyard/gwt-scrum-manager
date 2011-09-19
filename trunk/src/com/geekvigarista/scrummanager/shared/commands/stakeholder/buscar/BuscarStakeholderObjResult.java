package com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarStakeholderObjResult implements Result {
	private Stakeholder response;

	public BuscarStakeholderObjResult(Stakeholder response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private BuscarStakeholderObjResult() {
		// serialization
	}

	public Stakeholder getResponse() {
		return response;
	}

}
