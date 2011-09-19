package com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarStakeholderAction extends UnsecuredActionImpl<BuscarStakeholderListResult> {

	private String parametro;

	public BuscarStakeholderAction(String parametro) {
		super();
		this.parametro = parametro;
	}

	@SuppressWarnings("unused")
	private BuscarStakeholderAction() {
	}

	public String getParametro() {
		return parametro;
	}

}
