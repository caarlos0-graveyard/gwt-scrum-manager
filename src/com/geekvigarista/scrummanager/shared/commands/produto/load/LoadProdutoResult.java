package com.geekvigarista.scrummanager.shared.commands.produto.load;

import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.gwtplatform.dispatch.shared.Result;

public class LoadProdutoResult implements Result {
	private Produto response;

	public LoadProdutoResult(Produto response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private LoadProdutoResult() {
		// serialization
	}

	public Produto getResponse() {
		return response;
	}

}
