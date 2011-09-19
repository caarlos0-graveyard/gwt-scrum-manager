package com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarStakeholderByIdAction extends UnsecuredActionImpl<BuscarStakeholderObjResult> {

	private String id;

	public BuscarStakeholderByIdAction(String id) {
		super();
		this.id = id;
	}

	@SuppressWarnings("unused")
	private BuscarStakeholderByIdAction() {
	}

	public String getParametro() {
		return id;
	}

}
