package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderResult;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarStakeholderActionHandler implements ActionHandler<SalvarStakeholderAction, SalvarStakeholderResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoStakeholder dao = inj.getInstance(DaoStakeholder.class);
	
	@Override
	public SalvarStakeholderResult execute(SalvarStakeholderAction arg0, ExecutionContext arg1) throws ActionException
	{
		Stakeholder stk = dao.salvar(arg0.getStakeholder());
		return new SalvarStakeholderResult(stk);
	}
	
	@Override
	public Class<SalvarStakeholderAction> getActionType()
	{
		return SalvarStakeholderAction.class;
	}
	
	@Override
	public void undo(SalvarStakeholderAction arg0, SalvarStakeholderResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
