package com.geekvigarista.scrummanager.shared.commands.produto.load;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class LoadProdutoAction extends UnsecuredActionImpl<LoadProdutoResult> {

	private String id;

	public LoadProdutoAction(String id) {
		super();
		this.id = id;
	}

	@SuppressWarnings("unused")
	private LoadProdutoAction() {
	}

	public String getParametro() {
		return id;
	}

}
