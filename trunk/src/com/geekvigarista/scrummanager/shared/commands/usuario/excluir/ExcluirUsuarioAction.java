package com.geekvigarista.scrummanager.shared.commands.usuario.excluir;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class ExcluirUsuarioAction extends UnsecuredActionImpl<ExcluirUsuarioResult> {

	private Usuario usuario;

	public ExcluirUsuarioAction(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@SuppressWarnings("unused")
	private ExcluirUsuarioAction() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
