package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.Date;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;

/**
 * Teste para o dao Usuario
 * 
 * @author Raduq
 * 
 */
public class TesteDaoEncaminhamento
{
	/**
	 * Testa cadastrar....
	 */
	public static void cadastra(IDaoEncaminhamento dao)
	{
		IDaoStakeholder daoS = new DaoStakeholder();
		Stakeholder st = daoS.buscar("4e80f0a334922311d12d34d0");
		
		Encaminhamento e = new Encaminhamento();
		
		e.setData(new Date());
		e.setStakeholder(st);
		e.setStatus(StatusRequisito.AGUARDANDO);
		e.setTempoGasto(25);
		
		dao.salvar(e);
	}
	
	/**
	 * Busca e da print em todo mundo.
	 * 
	 * @param dao
	 */
	public static void buscaTodos(IDaoEncaminhamento dao)
	{
		for(Encaminhamento u : dao.buscarTodos())
		{
			System.out.println(u.getId().toString());
		}
	}
	
	/**
	 * Testa a exclusao excluindo todo mundo. Like a boss...
	 * 
	 * @param dao
	 */
	public static void exclui(IDaoEncaminhamento dao)
	{
		for(Encaminhamento u : dao.buscarTodos())
		{
			System.out.println(dao.excluir(u));
		}
	}
	
	public static void buscaById(IDaoEncaminhamento dao)
	{
		String id = "4e7b5fcbb42c020ec0b24925";
		Encaminhamento p = dao.buscar(id);
		System.out.println(p.getId());
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args)
	{
		IDaoEncaminhamento dao = new DaoEncaminhamento();
		
//		cadastra(dao);
		//		buscaTodos(dao);
		//		buscaById(dao);
		//		exclui(dao);
	}
}
