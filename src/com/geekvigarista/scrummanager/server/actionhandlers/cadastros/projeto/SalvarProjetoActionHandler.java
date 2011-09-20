package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
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
		Projeto p = dao.salvar(arg0.getProjeto());
		return new SalvarProjetoResult(p);
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
