package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LoginUsuarioAction;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginHandler implements ActionHandler<LoginUsuarioAction, BuscarUsuarioObjResult>
{
	
	private ServletContext servletContext;
	private Provider<HttpServletRequest> requestProvider;
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Inject
	LoginHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider)
	{
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}
	
	@Override
	public BuscarUsuarioObjResult execute(LoginUsuarioAction action, ExecutionContext context) throws ActionException
	{
		
		String login = action.getLogin();
		String senha = action.getSenha();
		
		// Verify that the input is valid.
		if(login == null || login.isEmpty())
		{
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			System.out.println("Ei, tire a mão do macaco.");
			throw new ActionException("Digite pelo menos o login, se voce ainda quizer logar.");
		}
		
		Usuario user = isUsuarioValido(login, senha);
		if(user == null)
		{
			List<String> erros = new ArrayList<String>();
			erros.add("Deu erro ao logar. Verifique seu login e senha");
			return new BuscarUsuarioObjResult(erros);
		}
		
		//XXX nao sei se isso funciona, teoriacamente guarda o cara na session
		requestProvider.get().getSession().setAttribute("USUARIO", user);
		
		return new BuscarUsuarioObjResult(user);
	}
	
	/* This method should be using spring-security or something like that */
	private Usuario isUsuarioValido(String username, String password)
	{
		Usuario user = dao.login(username, password);
		return user;
	}
	
	@Override
	public void undo(LoginUsuarioAction action, BuscarUsuarioObjResult result, ExecutionContext context) throws ActionException
	{
		// Not undoable
	}
	
	@Override
	public Class<LoginUsuarioAction> getActionType()
	{
		return LoginUsuarioAction.class;
	}
	
}
