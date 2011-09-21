package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarRequisitoActionHandler implements ActionHandler<SalvarRequisitoAction, SalvarRequisitoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoRequisito dao = inj.getInstance(DaoRequisito.class);
	
	@Override
	public SalvarRequisitoResult execute(SalvarRequisitoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Requisito req = dao.salvar(arg0.getRequisito());
		return new SalvarRequisitoResult(req);
	}
	
	@Override
	public Class<SalvarRequisitoAction> getActionType()
	{
		return SalvarRequisitoAction.class;
	}
	
	@Override
	public void undo(SalvarRequisitoAction arg0, SalvarRequisitoResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
