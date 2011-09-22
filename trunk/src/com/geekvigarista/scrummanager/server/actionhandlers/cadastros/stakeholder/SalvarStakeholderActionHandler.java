package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
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
		Stakeholder stk = arg0.getStakeholder();
		RetornoValidacao rv = valida(stk);
		if(!rv.isOk())
		{
			return new SalvarStakeholderResult(rv.getErros());
		}
		
		dao.salvar(stk);
		return new SalvarStakeholderResult(stk);
	}
	
	private RetornoValidacao valida(Stakeholder s)
	{
		List<String> erros = new ArrayList<String>();
		if(s == null)
		{
			erros.add("Pohh batman, stakeholder nao pode ta nulo");
		}
		else
		{
			if(s.getNome() == null || s.getNome().isEmpty())
			{
				erros.add("Coloque um nome para o stakeholder.");
			}
			
			if(s.getPapel() == null)
			{
				erros.add("Não sei como voce conseguiu tentar salvar sem papel. Adicione um papel.");
			}
			
			if(s.getUsuario() == null)
			{
				erros.add("Voce conseguiu chegar ate aqui sem usuario no stakeholder. Para de trolar ¬¬");
			}
		}
		RetornoValidacao rv = (erros.isEmpty()) ? new RetornoValidacao(true) : new RetornoValidacao(false, erros);
		return rv;
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
