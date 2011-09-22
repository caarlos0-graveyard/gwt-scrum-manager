package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

/**
 * Teste para o dao Usuario
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
		Stakeholder st = new Stakeholder();
		st.setNome("mario");
		st.setPapel(PapelStakeholder.ANALISTA_NEGOCIO);
		st.setUsuario(new Usuario("mariozao", "123456", "mario gomes"));
		
		Encaminhamento eAnterior = dao.buscar("4e7bc26b5aec94d9aa93512f");
				
		Encaminhamento e = new Encaminhamento();
		
		List<String> anexos = new ArrayList<String>();
		anexos.add("C:/porn");
		e.setAnexos(anexos);
		e.setData(new Date());
		e.setStakeholder(st);
		e.setStatus(StatusRequisito.EM_ANALISE);
		e.setTempoGasto(25);
		e.setEncaminhamentoAnterior(eAnterior);
		
		dao.salvar(e);
	}
	
	/**
	 * Busca e da print em todo mundo.
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
	 * Testa a exclusao excluindo todo mundo.
	 * Like a boss...
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
		
		cadastra(dao);
//		buscaTodos(dao);
//		buscaById(dao);
//		exclui(dao);
	}
}
