package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto;

import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProduto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProduto;
import com.geekvigarista.scrummanager.shared.commands.produto.busca.BuscaTodosProdutosAction;
import com.geekvigarista.scrummanager.shared.commands.produto.busca.BuscarProdutoListResult;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BuscarTodosProdutosActionHandler implements ActionHandler<BuscaTodosProdutosAction, BuscarProdutoListResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoProduto dao = inj.getInstance(DaoProduto.class);
	
	/**
	 * Busca todos os produtos
	 */
	@Override
	public BuscarProdutoListResult execute(BuscaTodosProdutosAction arg0, ExecutionContext arg1) throws ActionException
	{
		List<Produto> retorno = dao.buscarTodos();
		return new BuscarProdutoListResult(retorno);
	}
	
	@Override
	public Class<BuscaTodosProdutosAction> getActionType()
	{
		return BuscaTodosProdutosAction.class;
	}
	
	@Override
	public void undo(BuscaTodosProdutosAction arg0, BuscarProdutoListResult arg1, ExecutionContext arg2) throws ActionException
	{
		System.out.println("SalvarUsuarioActionHandler.undo()");
		
	}
	
}
