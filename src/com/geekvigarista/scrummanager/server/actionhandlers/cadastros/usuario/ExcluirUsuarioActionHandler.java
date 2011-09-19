package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.excluir.ExcluirUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.excluir.ExcluirUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ExcluirUsuarioActionHandler implements ActionHandler<ExcluirUsuarioAction, ExcluirUsuarioResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	DaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Override
	public ExcluirUsuarioResult execute(ExcluirUsuarioAction arg0, ExecutionContext arg1) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.execute()");
		//		Usuario u = dao.salvar(arg0.getUsuario());
		Usuario usuarioExcluir = arg0.getUsuario();
		boolean rv = validar(usuarioExcluir);
		if(!rv)
		{
			return new ExcluirUsuarioResult(false);
		}
		boolean resultado = dao.excluir(usuarioExcluir);
		return new ExcluirUsuarioResult(resultado);
	}
	
	/**
	 * Valida a exclusao.
	 * 
	 * @return true - pode continuar para excluir, false - nao pode excluir.
	 */
	public boolean validar(Usuario u)
	{
		if(u == null)
		{
			return false;
		}
		
		if(u.getId() == null || u.getId().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public Class<ExcluirUsuarioAction> getActionType()
	{
		return ExcluirUsuarioAction.class;
	}
	
	@Override
	public void undo(ExcluirUsuarioAction arg0, ExcluirUsuarioResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
