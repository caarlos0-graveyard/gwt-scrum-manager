package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;

/**
 * Teste para o dao Usuario
 * @author Raduq
 *
 */
public class TesteDaoRequisito 
{
	/**
	 * Testa cadastrar....
	 */
	public static void cadastra(IDaoRequisito dao)
	{
		IDaoEncaminhamento daoE = new DaoEncaminhamento();
		Encaminhamento e = daoE.buscar("4e7ba6ea5aec5a4675ee3114");
		
		List<Encaminhamento> encs = new ArrayList<Encaminhamento>();
		encs.add(e);
		
		Requisito r = new Requisito();
		
		List<String> anexos = new ArrayList<String>();
		anexos.add("C:/porn");
		
		r.setAnexos(anexos);
		r.setDataCadastro(new Date());
		r.setEncaminhamentos(encs);
		r.setPrioridade(PrioridadeRequisito.ALTA);
		r.setTempoEstimado(20);
		r.setTempoTotal(100);
		r.setTitulo("Estrombelete de pombo obeso");
		
		dao.salvar(r);
	}
	
	/**
	 * Busca e da print em todo mundo.
	 * @param dao
	 */
	public static void buscaTodos(IDaoRequisito dao)
	{
		for(Requisito u : dao.buscarTodos())
		{
			System.out.println(u.getId().toString());
		}

	}
	
	/**
	 * Testa a exclusao excluindo todo mundo.
	 * Like a boss...
	 * @param dao
	 */
	public static void exclui(IDaoRequisito dao)
	{
		for(Requisito u : dao.buscarTodos())
		{
			System.out.println(dao.excluir(u));
		}
	}
	
	public static void buscaById(IDaoRequisito dao)
	{
		String id = "4e7ba7495aecc95fabc30db3";
		Requisito p = dao.buscar(id);
		System.out.println(p.getId());
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
		IDaoRequisito dao = new DaoRequisito();
		
		cadastra(dao);
//		buscaTodos(dao);
//		buscaById(dao);
//		exclui(dao);
	}
}
