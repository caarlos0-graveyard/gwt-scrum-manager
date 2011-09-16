package com.geekvigarista.scrummanager.shared.commands.usuario;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarUsuario extends UnsecuredActionImpl<SalvarUsuarioResult> {

	private Usuario usuario;

	public SalvarUsuario(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@SuppressWarnings("unused")
	private SalvarUsuario() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
