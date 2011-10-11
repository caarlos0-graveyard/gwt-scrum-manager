package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.CarregarRequisitosNoProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class CarregarRequisitosNoProjetoActionHandler implements ActionHandler<CarregarRequisitosNoProjetoAction, LoadProjetoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoProjeto dao = inj.getInstance(DaoProjeto.class);
	
	@Override
	public LoadProjetoResult execute(CarregarRequisitosNoProjetoAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new LoadProjetoResult(dao.carregarRequisitos(arg0.getProjeto()));
	}
	
	@Override
	public Class<CarregarRequisitosNoProjetoAction> getActionType()
	{
		return CarregarRequisitosNoProjetoAction.class;
	}
	
	@Override
	public void undo(CarregarRequisitosNoProjetoAction arg0, LoadProjetoResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
