package com.geekvigarista.scrummanager.server.interfaces.dao;

import java.util.List;

import com.geekvigarista.scrummanager.server.beans.UsuarioPOJO;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Interface para o dao usuario, extendendo da interface IDao Aqui serao descritos metodos especificos do Dao Usuario
 * 
 * @author Raduq
 * 
 */
public interface IDaoUsuario extends IDao<Usuario, UsuarioPOJO>
{
	List<Usuario> buscarLike(String parametro);
	
	List<Usuario> buscaByLogin(String login, int limite);
	
	Usuario login(String login, String senha);
}
