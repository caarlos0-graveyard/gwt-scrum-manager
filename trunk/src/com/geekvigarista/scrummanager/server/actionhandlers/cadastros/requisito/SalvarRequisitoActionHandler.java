package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarRequisitoActionHandler implements ActionHandler<SalvarRequisitoAction, SalvarRequisitoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoRequisito dao = inj.getInstance(DaoRequisito.class);
	
	@Override
	public SalvarRequisitoResult execute(SalvarRequisitoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Requisito req = arg0.getRequisito();
		RetornoValidacao rv = valida(req);
		if(!rv.isOk())
		{
			return new SalvarRequisitoResult(rv.getErros());
		}
		dao.salvar(req);
		return new SalvarRequisitoResult(req);
	}
	
	private RetornoValidacao valida(Requisito r)
	{
		List<String> erros = new ArrayList<String>();
		if(r == null)
		{
			erros.add("Que coisa, seu requisito tava nulo.");
		}else
		{
			if(r.getTitulo() == null || r.getTitulo().isEmpty())
			{
				erros.add("O titulo nao pode ser nulo. Perdeu um ponto...");
			}
			if(r.getPrioridade() == null)
			{
				erros.add("O banco nao deixou salvar seu bagulho. Tava sem prioridade.");
			}
			if(r.getDataCadastro() == null)
			{
				erros.add("Coloque uma data de cadastro nessa coisa");
			}
			if(r.getTempoEstimado() < 0)
			{
				erros.add("Ei espertão, nao vai salvar um requisito sem tempo estimado nao !");
			}
			if(r.getTempoTotal() < 0)
			{
				erros.add("Ei manolo, coloque um tempo total também !");
			}
		}
		RetornoValidacao rv = (erros.isEmpty())?new RetornoValidacao(true):new RetornoValidacao(false,erros);
		return rv;
	}
	
	@Override
	public Class<SalvarRequisitoAction> getActionType()
	{
		return SalvarRequisitoAction.class;
	}
	
	@Override
	public void undo(SalvarRequisitoAction arg0, SalvarRequisitoResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
