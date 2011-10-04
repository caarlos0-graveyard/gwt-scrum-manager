package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.VerificaUsuarioLogadoAction;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class VerificaUsuarioLogadoHandler implements ActionHandler<VerificaUsuarioLogadoAction, BuscarUsuarioObjResult>
{
	
	private Provider<HttpServletRequest> requestProvider;
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Inject
	VerificaUsuarioLogadoHandler(Provider<HttpServletRequest> requestProvider)
	{
		this.requestProvider = requestProvider;
	}
	
	@Override
	public BuscarUsuarioObjResult execute(VerificaUsuarioLogadoAction action, ExecutionContext context) throws ActionException
	{
		Usuario user = (Usuario) requestProvider.get().getSession().getAttribute("USUARIO");
		if(user == null)
		{
			List<String> erros = new ArrayList<String>();
			erros.add("Sem usuario na sessao");
			return new BuscarUsuarioObjResult(erros);
		}
		return new BuscarUsuarioObjResult(user);
	}
	
	@Override
	public void undo(VerificaUsuarioLogadoAction action, BuscarUsuarioObjResult result, ExecutionContext context) throws ActionException
	{
		// Not undoable
	}
	
	@Override
	public Class<VerificaUsuarioLogadoAction> getActionType()
	{
		return VerificaUsuarioLogadoAction.class;
	}
	
}
