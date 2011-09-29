package com.geekvigarista.scrummanager.shared.commands.usuario.login;

import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class LoginUsuarioAction extends UnsecuredActionImpl<BuscarUsuarioObjResult> {

	private String login;
	private String senha;

	public LoginUsuarioAction(String login,String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	@SuppressWarnings("unused")
	private LoginUsuarioAction() {
	}

	public String getLogin() {
		return login;
	}

	public String getSenha()
	{
		return senha;
	}
	
	
}
