package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario;

import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioListResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BuscarUsuarioActionHandler implements ActionHandler<BuscarUsuarioAction, BuscarUsuarioListResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	/**
	 * Busca usuarios de acordo com o parametro.
	 * Se vem vazio ou nulo, busca todos.
	 */
	@Override
	public BuscarUsuarioListResult execute(BuscarUsuarioAction arg0, ExecutionContext arg1) throws ActionException
	{
		String parametro = arg0.getParametro();
		if(parametro == null || parametro.isEmpty())
		{
			List<Usuario> retorno = dao.buscarTodos();
			return new BuscarUsuarioListResult(retorno);
		}
		
		List<Usuario> retorno = dao.buscarLike(parametro);
		return new BuscarUsuarioListResult(retorno);
	}
		
	@Override
	public Class<BuscarUsuarioAction> getActionType()
	{
		return BuscarUsuarioAction.class;
	}
	
	@Override
	public void undo(BuscarUsuarioAction arg0, BuscarUsuarioListResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
