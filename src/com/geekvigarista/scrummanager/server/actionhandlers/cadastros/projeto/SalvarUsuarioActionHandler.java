package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.SalvarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.SalvarUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarUsuarioActionHandler implements ActionHandler<SalvarUsuarioAction, SalvarUsuarioResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	DaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Override
	public SalvarUsuarioResult execute(SalvarUsuarioAction arg0, ExecutionContext arg1) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.execute()");
		Usuario u = dao.salvar(arg0.getUsuario());
		return new SalvarUsuarioResult(u);
	}
	
	@Override
	public Class<SalvarUsuarioAction> getActionType()
	{
		return SalvarUsuarioAction.class;
	}
	
	@Override
	public void undo(SalvarUsuarioAction arg0, SalvarUsuarioResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
