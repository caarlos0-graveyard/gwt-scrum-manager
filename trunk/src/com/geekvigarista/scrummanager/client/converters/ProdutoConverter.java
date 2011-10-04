package com.geekvigarista.scrummanager.client.converters;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProdutoConverter;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoView;
import com.geekvigarista.scrummanager.shared.vos.Produto;

public class ProdutoConverter implements IProdutoConverter
{
	
	@Override
	public Produto convert(Produto t, CadProdutoView v)
	{
		Produto produto = new Produto();
		if(t == null)
			t = new Produto();
		
		if(t.getId() != null)
		{
			produto.setId(t.getId());
		}
		
		produto.setDescricao(t.getDescricao());
		
		return produto;
	}
	
	@Override
	public void updateView(Produto t, CadProdutoView v)
	{
		v.descricao().setText(t.getDescricao());
	}
	
}
