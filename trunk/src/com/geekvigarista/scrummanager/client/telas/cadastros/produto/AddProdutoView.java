package com.geekvigarista.scrummanager.client.telas.cadastros.produto;

import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddProdutoView extends ViewImpl implements CadProdutoView
{
	
	private static AddProdutoViewUiBinder uiBinder = GWT.create(AddProdutoViewUiBinder.class);
	
	Widget w;
	
	interface AddProdutoViewUiBinder extends UiBinder<Widget, AddProdutoView>
	{
	}
	
	public AddProdutoView()
	{
		w = uiBinder.createAndBindUi(this);
	}
	
	@UiField
	TextBox descricao;
	
	@UiField
	Anchor btCancelar;
	
	@UiField
	Button btSalvar;
	
	@UiField
	Button btNovo;
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public TextBox descricao()
	{
		return descricao;
	}
	
	@Override
	public Anchor cancelar()
	{
		return btCancelar;
	}
	
	@Override
	public Button salvar()
	{
		return btSalvar;
	}
	
	@Override
	public Button novo()
	{
		return btNovo;
	}
	
}
