package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.shared.commands.requisito.buscar.BuscarRequisitoByIdAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.buscar.BuscarRequisitoObjResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadRequisitoActionHandler implements ActionHandler<BuscarRequisitoByIdAction, BuscarRequisitoObjResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoRequisito dao = inj.getInstance(DaoRequisito.class);
	
	@Override
	public BuscarRequisitoObjResult execute(BuscarRequisitoByIdAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new BuscarRequisitoObjResult(dao.buscar(arg0.getParametro()));
	}
	
	@Override
	public Class<BuscarRequisitoByIdAction> getActionType()
	{
		return BuscarRequisitoByIdAction.class;
	}
	
	@Override
	public void undo(BuscarRequisitoByIdAction arg0, BuscarRequisitoObjResult arg1, ExecutionContext arg2) throws ActionException
	{
	}
	
}
