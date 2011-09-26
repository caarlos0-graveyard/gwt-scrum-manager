package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderByIdAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderObjResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadStakeholderActionHandler implements ActionHandler<BuscarStakeholderByIdAction, BuscarStakeholderObjResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoStakeholder dao = inj.getInstance(DaoStakeholder.class);
	
	@Override
	public BuscarStakeholderObjResult execute(BuscarStakeholderByIdAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new BuscarStakeholderObjResult(dao.buscar(arg0.getParametro()));
	}
	
	@Override
	public Class<BuscarStakeholderByIdAction> getActionType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void undo(BuscarStakeholderByIdAction arg0, BuscarStakeholderObjResult arg1, ExecutionContext arg2) throws ActionException
	{
		// TODO Auto-generated method stub
		
	}
	
}
