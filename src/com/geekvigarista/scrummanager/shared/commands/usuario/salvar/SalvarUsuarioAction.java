package com.geekvigarista.scrummanager.shared.commands.usuario.salvar;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarUsuarioAction extends UnsecuredActionImpl<SalvarUsuarioResult> {

	private Usuario usuario;

	public SalvarUsuarioAction(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@SuppressWarnings("unused")
	private SalvarUsuarioAction() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
