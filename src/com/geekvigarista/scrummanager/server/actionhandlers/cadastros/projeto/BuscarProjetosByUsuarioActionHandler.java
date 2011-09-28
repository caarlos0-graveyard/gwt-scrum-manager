package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoListResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetosByUsuarioAction;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BuscarProjetosByUsuarioActionHandler implements ActionHandler<BuscarProjetosByUsuarioAction, BuscarProjetoListResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoProjeto dao = inj.getInstance(DaoProjeto.class);
	
	/**
	 * Busca stakeholders de acordo com o parametro.
	 * Se vem vazio ou nulo, busca todos.
	 */
	@Override
	public BuscarProjetoListResult execute(BuscarProjetosByUsuarioAction arg0, ExecutionContext arg1) throws ActionException
	{
		Usuario usuario = arg0.getUsuario();
		if(usuario == null || usuario.getId() == null)
		{
			return new BuscarProjetoListResult(new ArrayList<ProjetoStakeholderDTO>());
		}
		
		List<ProjetoStakeholderDTO> retorno = dao.buscarByUsuario(usuario);
		return new BuscarProjetoListResult(retorno);
	}
		
	@Override
	public Class<BuscarProjetosByUsuarioAction> getActionType()
	{
		return BuscarProjetosByUsuarioAction.class;
	}
	
	@Override
	public void undo(BuscarProjetosByUsuarioAction arg0, BuscarProjetoListResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
