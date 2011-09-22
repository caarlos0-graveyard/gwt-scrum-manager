package com.geekvigarista.scrummanager.persistencia.dao;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Teste para o dao Usuario
 * @author Raduq
 *
 */
public class TesteDaoUsuario 
{
	/**
	 * Testa cadastrar....
	 */
	public static void cadastra(IDaoUsuario dao)
	{
		Usuario u = new Usuario();
		u.setNome("pedrao");
		u.setSenha("pedrao");
		u.setLogin("pedrao");
		dao.salvar(u);
	}
	
	/**
	 * Busca e da print em todo mundo.
	 * @param dao
	 */
	public static void buscaTodos(IDaoUsuario dao)
	{
		for(Usuario u : dao.buscarTodos())
		{
			System.out.println(u.getNome() + "  " + u.getId());
		}
	}
	
	/**
	 * Testa a exclusao excluindo todo mundo.
	 * Like a boss...
	 * @param dao
	 */
	public static void exclui(IDaoUsuario dao)
	{
		for(Usuario u : dao.buscarTodos())
		{
			System.out.println(dao.excluir(u));
		}
	}
	
	public static void buscaById(IDaoUsuario dao)
	{
		String id = "4e72960f7f3d9c3b7fe27673";
		Usuario u = dao.buscar(id);
		System.out.println(u.getNome());
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
		IDaoUsuario dao = new DaoUsuario(MongoConnection.getDatastore());
		
//		cadastra(dao);
//		buscaTodos(dao);
//		buscaById(dao);
//		exclui(dao);
	}
}
