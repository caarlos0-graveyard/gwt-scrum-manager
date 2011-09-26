package com.geekvigarista.scrummanager.persistencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
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
		p.setNome("projeto dogola");
		p.setDataInicio(new Date());
		p.setDataFim(new Date());
		
		IDaoStakeholder daoStake = new DaoStakeholder();
		List<Stakeholder> stakes = daoStake.buscarTodos();
		
		p.setStakeholders(stakes);
		dao.salvar(p);
	}
	
	public static void cadastra2(IDaoProjeto dao)
	{
		Projeto p = dao.buscar("4e80ff3b349291d0536851ca");
		Requisito r = new Requisito();
		List<String> anexos = new ArrayList<String>();
		anexos.add("dogola");
		r.setAnexos(anexos);
		r.setDataCadastro(new Date());
		r.setDescricao("Dogola descricao");
		
		Encaminhamento e = new Encaminhamento();
		e.setAnexos(anexos);
		e.setData(new Date());
		e.setEncaminhamentoAnterior(null);
		e.setStatus(StatusRequisito.AGUARDANDO);
		e.setStakeholder(p.getStakeholders().get(0));
		e.setTempoGasto(23);
		
		List<Encaminhamento> encs = new ArrayList<Encaminhamento>();
		encs.add(e);
		
		r.setEncaminhamentos(encs);
		r.setPrioridade(PrioridadeRequisito.BAIXA);
		r.setTempoEstimado(34);
		r.setTempoTotal(3452);
		r.setTitulo("Dolora requisito");
		
		List<Requisito> reqs = new ArrayList<Requisito>();
		reqs.add(r);
		
		p.setRequisitos(reqs);
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
			System.out.println(u.getStakeholders().size());
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
		String id = "4e80ff3b349291d0536851ca";
		Projeto p = dao.buscar(id);
		System.out.println(p.getNome());
	}
	
	public static void buscaByUsuario(IDaoProjeto dao)
	{
		IDaoUsuario daoU = new DaoUsuario();
		Usuario u = daoU.buscar("4e72960f7f3d9c3b7fe27673");
		for(Projeto p : dao.buscarByUsuario(u))
		{
			System.out.println(p.getNome());
		}
	}
	
	/**
	 * Testa a parada...
	 */
	public static void main(String[] args) 
	{
		IDaoProjeto dao = new DaoProjeto();
		
//		buscaByUsuario(dao);
//		cadastra(dao);
//		cadastra2(dao);
//		buscaTodos(dao);
		buscaById(dao);
//		exclui(dao);
	}
}
