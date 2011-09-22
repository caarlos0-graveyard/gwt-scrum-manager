package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
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
		Encaminhamento enc = arg0.getEncaminhamento();
		RetornoValidacao rv = valida(enc);
		if(!rv.isOk())
		{
			return new SalvarEncaminhamentoResult(rv.getErros());
		}
		dao.salvar(enc);
		return new SalvarEncaminhamentoResult(enc);
	}
	
	private RetornoValidacao valida(Encaminhamento e)
	{
		List<String> erros = new ArrayList<String>();
		if(e == null)
		{
			erros.add("Paro paro paro paro ! O encaminhamento ta nulo...");
		}else
		{
			if(e.getData() == null)
			{
				erros.add("Ta sem data. Coloca uma aew!");
			}
			
			if(e.getStakeholder() == null)
			{
				erros.add("O encaminhamento ta sem stakeholder. Se vira ai!");
			}
			
			if(e.getStatus() == null)
			{
				erros.add("Nao tem como deixar sem Status. Tu é foda !");
			}
			
			if(e.getTempoGasto() < 0 )
			{
				erros.add("Deve ter algum tempo gasto !");
			}
			
			if(e.getId() != null && e.getEncaminhamentoAnterior().getId().endsWith(e.getId()))
			{
				erros.add("Poxa cara, encaminhamento anterior nao pode ser o mesmo que este proprio.");
			}
		}
		RetornoValidacao rv = (erros.isEmpty())?new RetornoValidacao(true):new RetornoValidacao(false, erros);
		return rv;
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
