package com.geekvigarista.scrummanager.shared.commands.produto.salvar;

import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarProdutoAction extends UnsecuredActionImpl<SalvarProdutoResult> {

	private Produto produto;

	public SalvarProdutoAction(Produto produto) {
		super();
		this.produto = produto;
	}

	@SuppressWarnings("unused")
	private SalvarProdutoAction() {
	}

	public Produto getProduto() {
		return produto;
	}

}
