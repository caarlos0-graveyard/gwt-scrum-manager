package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir.ExcluirEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir.ExcluirEncaminhamentoResult;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ExcluirEncaminhamentoActionHandler implements ActionHandler<ExcluirEncaminhamentoAction, ExcluirEncaminhamentoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoEncaminhamento dao = inj.getInstance(DaoEncaminhamento.class);
	
	@Override
	public ExcluirEncaminhamentoResult execute(ExcluirEncaminhamentoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Encaminhamento enc = arg0.getEncaminhamento();
		boolean rv = validar(enc);
		if(!rv){
			return new ExcluirEncaminhamentoResult(false);
		}
		boolean retorno = dao.excluir(enc);
		return new ExcluirEncaminhamentoResult(retorno);
	}
	
	/**
	 * Valida a exclusao.
	 * 
	 * @return true - pode continuar para excluir, false - nao pode excluir.
	 */
	public boolean validar(Encaminhamento enc)
	{
		if(enc == null)
		{
			return false;
		}
		
		if(enc.getId() == null || enc.getId().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public Class<ExcluirEncaminhamentoAction> getActionType()
	{
		return ExcluirEncaminhamentoAction.class;
	}
	
	@Override
	public void undo(ExcluirEncaminhamentoAction arg0, ExcluirEncaminhamentoResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
