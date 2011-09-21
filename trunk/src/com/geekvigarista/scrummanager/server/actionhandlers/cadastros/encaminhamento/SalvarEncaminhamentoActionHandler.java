package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar.SalvarEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar.SalvarEncaminhamentoResult;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarEncaminhamentoActionHandler implements ActionHandler<SalvarEncaminhamentoAction, SalvarEncaminhamentoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoEncaminhamento dao = inj.getInstance(DaoEncaminhamento.class);
	
	@Override
	public SalvarEncaminhamentoResult execute(SalvarEncaminhamentoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Encaminhamento enc = dao.salvar(arg0.getEncaminhamento());
		return new SalvarEncaminhamentoResult(enc);
	}
	
	@Override
	public Class<SalvarEncaminhamentoAction> getActionType()
	{
		return SalvarEncaminhamentoAction.class;
	}
	
	@Override
	public void undo(SalvarEncaminhamentoAction arg0, SalvarEncaminhamentoResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
