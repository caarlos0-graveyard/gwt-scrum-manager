package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProduto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProduto;
import com.geekvigarista.scrummanager.shared.commands.produto.load.LoadProdutoAction;
import com.geekvigarista.scrummanager.shared.commands.produto.load.LoadProdutoResult;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadProdutoActionHandler implements ActionHandler<LoadProdutoAction, LoadProdutoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	IDaoProduto dao = inj.getInstance(DaoProduto.class);
	
	@Override
	public LoadProdutoResult execute(LoadProdutoAction arg0, ExecutionContext arg1) throws ActionException
	{
		return new LoadProdutoResult(dao.buscar(arg0.getParametro()));
	}
	
	@Override
	public Class<LoadProdutoAction> getActionType()
	{
		return LoadProdutoAction.class;
	}
	
	@Override
	public void undo(LoadProdutoAction arg0, LoadProdutoResult arg1, ExecutionContext arg2) throws ActionException
	{
		// TODO Auto-generated method stub
		
	}
	
}
