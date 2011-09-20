package com.geekvigarista.scrummanager.persistencia.dao;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Teste para o dao Usuario
 * @author Raduq
 *
 */
public class TesteDaoStakeholder 
{
	/**
	 * Testa cadastrar....
	 */
	public static void cadastra(IDaoStakeholder dao)
	{
		Stakeholder stk = new Stakeholder();
		stk.setNome("joao stk");
		stk.setPapel(PapelStakeholder.GP);
		
		IDaoUsuario daoUsuario = new DaoUsuario();
		Usuario usu = daoUsuario.buscar("4e72960f7f3d9c3b7fe27673");
		stk.setUsuario(usu);
		dao.salvar(stk);
	}
	
	/**
	 * Busca e da print em todo mundo.
	 * @param dao
	 */
	public static void buscaTodos(IDaoStakeholder dao)
	{
		for(Stakeholder u : dao.buscarTodos())
		{
			System.out.println(u.getNome() + "  " + u.getId());
		}
	}
	
	/**
	 * Testa a exclusao excluindo todo mundo.
	 * Like a boss...
	 * @param dao
	 */
	public static void exclui(IDaoStakeholder dao)
	{
		for(Stakeholder u : dao.buscarTodos())
		{
			System.out.println(dao.excluir(u));
		}
	}
	
	public static void buscaById(IDaoStakeholder dao)
	{
		String id = "4e77c99cd1ccd51eb6fa6fdb";
		Stakeholder stk = dao.buscar(id);
		System.out.println(stk.getNome());
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
//		IDaoUsuario dao = new DaoUsuario(MongoConnection.getDatastore());
		IDaoStakeholder dao = new DaoStakeholder();
		
//		cadastra(dao);
		buscaTodos(dao);
//		buscaById(dao);
//		exclui(dao);
	}
}
