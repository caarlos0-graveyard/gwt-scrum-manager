package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoResult;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ExcluirRequisitoActionHandler implements ActionHandler<ExcluirRequisitoAction, ExcluirRequisitoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoRequisito dao = inj.getInstance(DaoRequisito.class);
	
	@Override
	public ExcluirRequisitoResult execute(ExcluirRequisitoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Requisito req = arg0.getRequisito();
		boolean rv = validar(req);
		if(!rv){
			return new ExcluirRequisitoResult(false);
		}
		boolean retorno = dao.excluir(req);
		return new ExcluirRequisitoResult(retorno);
	}
	
	/**
	 * Valida a exclusao.
	 * 
	 * @return true - pode continuar para excluir, false - nao pode excluir.
	 */
	public boolean validar(Requisito req)
	{
		if(req == null)
		{
			return false;
		}
		
		if(req.getId() == null || req.getId().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public Class<ExcluirRequisitoAction> getActionType()
	{
		return ExcluirRequisitoAction.class;
	}
	
	@Override
	public void undo(ExcluirRequisitoAction arg0, ExcluirRequisitoResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
