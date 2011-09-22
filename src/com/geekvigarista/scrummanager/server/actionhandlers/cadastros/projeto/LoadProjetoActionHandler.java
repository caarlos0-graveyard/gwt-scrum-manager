package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadProjetoActionHandler implements ActionHandler<LoadProjetoAction, LoadProjetoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoProjeto dao = inj.getInstance(DaoProjeto.class);
	
	@Override
	public LoadProjetoResult execute(LoadProjetoAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new LoadProjetoResult(dao.buscar(arg0.getId()));
	}
	
	@Override
	public Class<LoadProjetoAction> getActionType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void undo(LoadProjetoAction arg0, LoadProjetoResult arg1, ExecutionContext arg2) throws ActionException
	{
		// TODO Auto-generated method stub
		
	}
	
}
