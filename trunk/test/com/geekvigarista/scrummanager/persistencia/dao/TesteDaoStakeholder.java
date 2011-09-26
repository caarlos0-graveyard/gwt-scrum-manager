package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
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
		stk.setNome("stake pintudao tetudao");
		stk.setPapel(PapelStakeholder.DESENVOLVEDOR);
		
		IDaoUsuario daoUsuario = new DaoUsuario();
		Usuario usu = daoUsuario.buscar("4e7bbcca5aec04e4e8389a40");
		usu.setLogin("locao tetudao");
//		Usuario usu = new Usuario();
//		usu.setLogin("locao");
//		usu.setNome("locao");
//		usu.setSenha("locao");
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
			System.out.println(u.getNome() + "  " + u.getId() + "  " + u.getUsuario().getNome());
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
		String id = "4e80f0a334922311d12d34d0";
		Stakeholder stk = dao.buscar(id);
		System.out.println(stk.getNome() + " - Usuario -> " + stk.getUsuario().getNome());
	}
	
	public static void adicionarProjetoEmTodosOsStakes(IDaoStakeholder dao)
	{
		IDaoProjeto daoP = new DaoProjeto();
		Projeto p = daoP.buscar("4e790c27aca6d98745b6a143");
		List<Stakeholder> stks = dao.buscarTodos();
		
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
//		IDaoUsuario dao = new DaoUsuario(MongoConnection.getDatastore());
		IDaoStakeholder dao = new DaoStakeholder();
		
//		adicionarProjetoEmTodosOsStakes(dao);
//		cadastra(dao);
//		buscaTodos(dao);
		buscaById(dao);
//		exclui(dao);
	}
}
