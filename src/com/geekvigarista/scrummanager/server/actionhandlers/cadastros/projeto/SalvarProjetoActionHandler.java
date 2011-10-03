package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarProjetoActionHandler implements ActionHandler<SalvarProjetoAction, SalvarProjetoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoProjeto dao = inj.getInstance(DaoProjeto.class);
	
	@Override
	public SalvarProjetoResult execute(SalvarProjetoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Projeto p = arg0.getProjeto();
		RetornoValidacao rv = valida(p);
		if(!rv.isOk())
		{
			return new SalvarProjetoResult(rv.getErros());
		}
		p = dao.salvar(p);
		return new SalvarProjetoResult(p);
	}
	
	public RetornoValidacao valida(Projeto p)
	{
		List<String> erros = new ArrayList<String>();
		if(p == null)
		{
			erros.add("Tu conseguiu dar nullpointer no projeto. Vai pro mundo cara !");
		}else
		{
			if(p.getDataFim() == null)
			{
				erros.add("Projetos com tempo para terminar infinitos não são permitidos.");
			}
			if(p.getDataInicio() == null)
			{
				erros.add("Eu acho, que o projeto deve iniciar algum dia. Larga de ser vagabundo e coloca a dataInicio!");
			}
			if(p.getNome() == null || p.getNome().trim().isEmpty())
			{
				erros.add("Projetos sem nome não sao permitidos."); 
			}
		}
		RetornoValidacao rv = (erros.isEmpty())?new RetornoValidacao(true):new RetornoValidacao(false, erros);
		return rv;
	}
	
	@Override
	public Class<SalvarProjetoAction> getActionType()
	{
		return SalvarProjetoAction.class;
	}
	
	@Override
	public void undo(SalvarProjetoAction arg0, SalvarProjetoResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
