package com.geekvigarista.scrummanager.client.converters;

import com.geekvigarista.scrummanager.client.converters.interfaces.IUsuarioConverter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.AddUserView;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.user.client.ui.HasValue;

public class UsuarioConverter implements IUsuarioConverter
{
	private Usuario convert(HasValue<String> nome, HasValue<String> login, HasValue<String> senha, HasValue<Boolean> administrador, HasValue<String> confSenha)
	{
		Usuario u = new Usuario();
		u.setNome(nome.getValue());
		u.setLogin(login.getValue());
		u.setSenha(senha.getValue());
		u.setAdministrador(administrador.getValue());
		u.setConfirmacaoSenha(confSenha.getValue());
		return u;
	}
	
	@Override
	public Usuario convert(Usuario u, AddUserView v)
	{
		Usuario user = convert(v.getNome(), v.getLogin(), v.getSenha(), v.getAdministrador(), v.getConfSenha());
		if(u != null && u.getId() != null)
		{
			user.setId(u.getId());
		}
		return user;
	}
	
	@Override
	public void updateView(Usuario u, AddUserView v)
	{
		v.getAdministrador().setValue(u.isAdministrador());
		v.getConfSenha().setValue(u.getSenha());
		v.getSenha().setValue(u.getSenha());
		v.getLogin().setValue(u.getLogin());
		v.getNome().setValue(u.getNome());
	}
}
