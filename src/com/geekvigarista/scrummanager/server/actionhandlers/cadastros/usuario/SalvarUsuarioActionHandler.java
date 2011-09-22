package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarUsuarioActionHandler implements ActionHandler<SalvarUsuarioAction, SalvarUsuarioResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoUsuario dao = inj.getInstance(DaoUsuario.class);
	
	@Override
	public SalvarUsuarioResult execute(SalvarUsuarioAction arg0, ExecutionContext arg1) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.execute()");
		Usuario u = arg0.getUsuario();
		RetornoValidacao rv = valida(u);
		if(!rv.isOk())
		{
			return new SalvarUsuarioResult(rv.getErros());
		}
		
		dao.salvar(u);
		return new SalvarUsuarioResult(u);
	}
	
	/**
	 * Valida as regras de negocio para salvar usuario.
	 * 
	 * @param u
	 * @return
	 */
	private RetornoValidacao valida(Usuario u)
	{
		List<String> erros = new ArrayList<String>();
		if(u == null)
		{
			erros.add("O usuário não pode estar nulo, animal!");
		}
		else
		{
			if(u.getLogin() == null || u.getLogin().isEmpty())
			{
				erros.add("O usuário deve ter um login, quer logar no sistema como ?");
			}
			else if(!dao.buscaByLogin(u.getLogin(), 1).isEmpty())
			{
				erros.add("Já existe um usuario com este login. Escolha outro.");
			}
			
			if(u.getNome() == null || u.getNome().isEmpty())
			{
				erros.add("O usuário deve ter nome. Não crie um zé ninguem...");
			}
			
			if(u.getSenha() == null || u.getSenha().isEmpty())
			{
				erros.add("Escolha pelomenos uma senha.");
			}
			else if(u.getSenha().length() < 6)
			{
				erros.add("A senha deve ter pelo menos 6 caracteres");
			}
		}
		
		RetornoValidacao rv = (erros.isEmpty()) ? new RetornoValidacao(true) : new RetornoValidacao(false, erros);
		return rv;
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
