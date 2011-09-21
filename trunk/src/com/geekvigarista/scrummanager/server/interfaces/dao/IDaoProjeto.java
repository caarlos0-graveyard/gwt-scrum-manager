package com.geekvigarista.scrummanager.server.interfaces.dao;

import java.util.List;

import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Interface para o dao projeto, extendendo da interface IDao
 * Aqui serao descritos metodos especificos do Dao Projeto
 * @author Raduq
 *
 */
public interface IDaoProjeto extends IDao<Projeto, ProjetoPOJO> 
{
	//TODO, metodos especificos irão aqui..
	List<Projeto> buscarLike(String parametro);	
	
	List<Projeto> buscarByUsuario(Usuario usuario);	

}
