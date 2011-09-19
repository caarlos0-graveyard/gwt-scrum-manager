package com.geekvigarista.scrummanager.shared.commands.usuario.buscar;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarUsuarioByIdAction extends UnsecuredActionImpl<BuscarUsuarioObjResult> {

	private String id;

	public BuscarUsuarioByIdAction(String id) {
		super();
		this.id = id;
	}

	@SuppressWarnings("unused")
	private BuscarUsuarioByIdAction() {
	}

	public String getParametro() {
		return id;
	}

}
