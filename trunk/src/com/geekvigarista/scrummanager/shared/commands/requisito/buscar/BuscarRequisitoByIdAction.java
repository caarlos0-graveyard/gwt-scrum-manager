package com.geekvigarista.scrummanager.shared.commands.requisito.buscar;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarRequisitoByIdAction extends UnsecuredActionImpl<BuscarRequisitoObjResult> {

	private String id;

	public BuscarRequisitoByIdAction(String id) {
		super();
		this.id = id;
	}

	@SuppressWarnings("unused")
	private BuscarRequisitoByIdAction() {
	}

	public String getParametro() {
		return id;
	}

}
