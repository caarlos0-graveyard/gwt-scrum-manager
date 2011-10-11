package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoLikeAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoListResult;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BuscarProjetoLikeActionHandler implements ActionHandler<BuscarProjetoLikeAction, BuscarProjetoListResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoProjeto dao = inj.getInstance(DaoProjeto.class);
	
	@Override
	public BuscarProjetoListResult execute(BuscarProjetoLikeAction arg0, ExecutionContext arg1) throws ActionException
	{
		
		List<Projeto> retorno = dao.buscarLike(arg0.getParametro());
		List<ProjetoStakeholderDTO> retornoDTO = new ArrayList<ProjetoStakeholderDTO>();
		for(Projeto p : retorno)
		{
			retornoDTO.add(new ProjetoStakeholderDTO(p));
		}
		return new BuscarProjetoListResult(retornoDTO);
	}
	
	@Override
	public Class<BuscarProjetoLikeAction> getActionType()
	{
		return BuscarProjetoLikeAction.class;
	}
	
	@Override
	public void undo(BuscarProjetoLikeAction arg0, BuscarProjetoListResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
