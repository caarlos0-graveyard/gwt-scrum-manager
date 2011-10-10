package com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.server.guice.DAOModule;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProduto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProduto;
import com.geekvigarista.scrummanager.server.validacoes.RetornoValidacao;
import com.geekvigarista.scrummanager.shared.commands.produto.salvar.SalvarProdutoAction;
import com.geekvigarista.scrummanager.shared.commands.produto.salvar.SalvarProdutoResult;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SalvarProdutoActionHandler implements ActionHandler<SalvarProdutoAction, SalvarProdutoResult>
{
	
	Injector inj = Guice.createInjector(new DAOModule());
	
	IDaoProduto dao = inj.getInstance(DaoProduto.class);
	
	@Override
	public SalvarProdutoResult execute(SalvarProdutoAction arg0, ExecutionContext arg1) throws ActionException
	{
		Produto p = arg0.getProduto();
		RetornoValidacao rv = valida(p);
		if(!rv.isOk())
		{
			return new SalvarProdutoResult(rv.getErros());
		}
		
		dao.salvar(p);
		return new SalvarProdutoResult(p);
	}
	
	/**
	 * Valida as regras de negocio para salvar usuario.
	 * 
	 * @param u
	 * @return
	 */
	private RetornoValidacao valida(Produto p)
	{
		List<String> erros = new ArrayList<String>();
		if(p == null)
		{
			erros.add("O produto nao pode ta nulo.!");
		}
		else
		{
			if(p.getDescricao() == null || p.getDescricao().trim().isEmpty())
			{
				erros.add("Coloque uma descrição nesse produto ai");
			}
		}
		
		RetornoValidacao rv = (erros.isEmpty()) ? new RetornoValidacao(true) : new RetornoValidacao(false, erros);
		return rv;
	}
	
	@Override
	public Class<SalvarProdutoAction> getActionType()
	{
		return SalvarProdutoAction.class;
	}
	
	@Override
	public void undo(SalvarProdutoAction arg0, SalvarProdutoResult arg1, ExecutionContext arg2) throws ActionException
	{
		
	}
	
}
