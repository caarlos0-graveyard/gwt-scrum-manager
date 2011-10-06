package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder;

import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderListResult;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BuscarStakeholderActionHandler implements ActionHandler<BuscarStakeholderAction, BuscarStakeholderListResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoStakeholder dao = inj.getInstance(DaoStakeholder.class);
	
	/**
	 * Busca stakeholders de acordo com o parametro. Se vem vazio ou nulo, busca todos.
	 */
	@Override
	public BuscarStakeholderListResult execute(BuscarStakeholderAction arg0, ExecutionContext arg1) throws ActionException
	{
		try
		{
			
			String parametro = arg0.getParametro();
			if(parametro == null || parametro.isEmpty())
			{
				List<Stakeholder> retorno = dao.buscarTodos();
				return new BuscarStakeholderListResult(retorno);
			}
			
			List<Stakeholder> retorno = dao.buscarLike(parametro);
			return new BuscarStakeholderListResult(retorno);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Class<BuscarStakeholderAction> getActionType()
	{
		return BuscarStakeholderAction.class;
	}
	
	@Override
	public void undo(BuscarStakeholderAction arg0, BuscarStakeholderListResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
