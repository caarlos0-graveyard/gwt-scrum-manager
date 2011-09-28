package com.geekvigarista.scrummanager.shared.commands.produto.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarProdutoResult implements Result {
	private Produto response;
	private List<String> erros;

	public SalvarProdutoResult(Produto response) {
		super();
		this.response = response;
	}
	
	public SalvarProdutoResult(List<String> erros) {
		super(); 
		this.erros = erros;
	}
	
	public SalvarProdutoResult(Produto response,List<String> erros) {
		super();
		this.response = response;
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarProdutoResult() {
		// serialization
	}

	public Produto getResponse() {
		return response;
	}
	
	public List<String> getErros()
	{
		return erros;
	}

}
