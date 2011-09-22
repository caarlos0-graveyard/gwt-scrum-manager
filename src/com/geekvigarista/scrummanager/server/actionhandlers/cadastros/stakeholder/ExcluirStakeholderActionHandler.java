package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir.ExcluirStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir.ExcluirStakeholderResult;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ExcluirStakeholderActionHandler implements ActionHandler<ExcluirStakeholderAction, ExcluirStakeholderResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoStakeholder dao = inj.getInstance(DaoStakeholder.class);
	
	@Override
	public ExcluirStakeholderResult execute(ExcluirStakeholderAction arg0, ExecutionContext arg1) throws ActionException
	{
		Stakeholder stk = arg0.getStakeholder();
		boolean rv = validar(stk);
		if(!rv){
			return new ExcluirStakeholderResult(false);
		}
		boolean retorno = dao.excluir(stk);
		return new ExcluirStakeholderResult(retorno);
	}
	
	/**
	 * Valida a exclusao.
	 * 
	 * @return true - pode continuar para excluir, false - nao pode excluir.
	 */
	public boolean validar(Stakeholder stk)
	{
		if(stk == null)
		{
			return false;
		}
		
		if(stk.getId() == null || stk.getId().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public Class<ExcluirStakeholderAction> getActionType()
	{
		return ExcluirStakeholderAction.class;
	}
	
	@Override
	public void undo(ExcluirStakeholderAction arg0, ExcluirStakeholderResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
