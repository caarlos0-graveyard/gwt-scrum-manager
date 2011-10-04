package com.geekvigarista.scrummanager.shared.commands.produto.busca;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarProdutoListResult implements Result {
	private List<Produto> response;

	public BuscarProdutoListResult(List<Produto> response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private BuscarProdutoListResult() {
		// serialization
	}

	public List<Produto> getResponse() {
		return response;
	}

}
