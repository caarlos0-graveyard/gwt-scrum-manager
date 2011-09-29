package com.geekvigarista.scrummanager.client.converters;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.user.client.ui.HasValue;

public class UsuarioConverter
{
	public static Usuario convert(HasValue<String> nome, HasValue<String> login, HasValue<String> senha, HasValue<Boolean> administrador)
	{
		Usuario u = new Usuario();
		u.setNome(nome.getValue());
		u.setLogin(login.getValue());
		u.setSenha(login.getValue());
		u.setAdministrador(administrador.getValue());
		return u;
	}
}
