package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

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
		
		Stakeholder stk = new Stakeholder();
		stk.setNome("rushahaha");
		stk.setPapel(PapelStakeholder.DESENVOLVEDOR);
//		stk.setUsuario(new Usuario("rushahaha", "rushahaha", "rushahaha"));
		
		Encaminhamento e = new Encaminhamento();
		e.setData( new Date());
		e.setStakeholder(stk);
		e.setStatus(StatusRequisito.AGUARDANDO);
		e.setTempoGasto(0);
		
		List<Encaminhamento> encs = new ArrayList<Encaminhamento>();
		encs.add(e);
		
		Requisito r = new Requisito();
		
		r.setDataCadastro(new Date());
		r.setEncaminhamentos(encs);
		r.setPrioridade(PrioridadeRequisito.ALTA);
		r.setTempoEstimado(25);
		r.setTempoTotal(204);
		r.setTitulo("Enfia no rushahaha");
		
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
