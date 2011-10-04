package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LogoutUsuarioAction;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LogoutHandler implements ActionHandler<LogoutUsuarioAction, BuscarUsuarioObjResult>
{
	private Provider<HttpServletRequest> requestProvider;
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Inject
	LogoutHandler(Provider<HttpServletRequest> requestProvider)
	{
		this.requestProvider = requestProvider;
	}
	
	@Override
	public BuscarUsuarioObjResult execute(LogoutUsuarioAction action, ExecutionContext context) throws ActionException
	{
		try
		{
			requestProvider.get().getSession().removeAttribute("USUARIO");
			return new BuscarUsuarioObjResult(new Usuario());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			List<String> erros = new ArrayList<String>();
			erros.add("Deu merda ao deslogar");
			return new BuscarUsuarioObjResult(erros);
		}
	}
	
	@Override
	public void undo(LogoutUsuarioAction action, BuscarUsuarioObjResult result, ExecutionContext context) throws ActionException
	{
		// Not undoable
	}
	
	@Override
	public Class<LogoutUsuarioAction> getActionType()
	{
		return LogoutUsuarioAction.class;
	}
	
}
