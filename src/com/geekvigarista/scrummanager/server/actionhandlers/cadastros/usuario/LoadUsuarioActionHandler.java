package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioByIdAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadUsuarioActionHandler implements ActionHandler<BuscarUsuarioByIdAction, BuscarUsuarioObjResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Override
	public BuscarUsuarioObjResult execute(BuscarUsuarioByIdAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new BuscarUsuarioObjResult(dao.buscar(arg0.getParametro()));
	}
	
	@Override
	public Class<BuscarUsuarioByIdAction> getActionType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void undo(BuscarUsuarioByIdAction arg0, BuscarUsuarioObjResult arg1, ExecutionContext arg2) throws ActionException
	{
		// TODO Auto-generated method stub
		
	}
	
}
