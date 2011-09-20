package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Teste para o dao Usuario
 * @author Raduq
 *
 */
public class TesteDaoProjeto 
{
	/**
	 * Testa cadastrar....
	 */
	public static void cadastra(IDaoProjeto dao)
	{
		Projeto p = new Projeto();
		p.setNome("projeto abc");
		p.setDataInicio(new Date());
		p.setDataFim(new Date());
		
		IDaoStakeholder daoStake = new DaoStakeholder();
		List<Stakeholder> stakes = daoStake.buscarTodos();
		
		p.setStakeholders(stakes);
		dao.salvar(p);
	}
	
	/**
	 * Busca e da print em todo mundo.
	 * @param dao
	 */
	public static void buscaTodos(IDaoProjeto dao)
	{
		for(Projeto u : dao.buscarTodos())
		{
			System.out.println(u.getNome() + "  " + u.getId());
		}
	}
	
	/**
	 * Testa a exclusao excluindo todo mundo.
	 * Like a boss...
	 * @param dao
	 */
	public static void exclui(IDaoProjeto dao)
	{
		for(Projeto u : dao.buscarTodos())
		{
			System.out.println(dao.excluir(u));
		}
	}
	
	public static void buscaById(IDaoProjeto dao)
	{
		String id = "4e78ba2f05c6beb01ec2425b";
		Projeto p = dao.buscar(id);
		System.out.println(p.getNome());
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
		IDaoProjeto dao = new DaoProjeto();
		
//		cadastra(dao);
		buscaTodos(dao);
//		buscaById(dao);
//		exclui(dao);
	}
}
