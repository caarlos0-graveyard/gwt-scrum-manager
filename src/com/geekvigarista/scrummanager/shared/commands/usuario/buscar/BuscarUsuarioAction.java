package com.geekvigarista.scrummanager.shared.commands.usuario.buscar;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class BuscarUsuarioAction extends UnsecuredActionImpl<BuscarUsuarioListResult> {

	private String parametro;

	public BuscarUsuarioAction(String parametro) {
		super();
		this.parametro = parametro;
	}

	@SuppressWarnings("unused")
	private BuscarUsuarioAction() {
	}

	public String getParametro() {
		return parametro;
	}

}
