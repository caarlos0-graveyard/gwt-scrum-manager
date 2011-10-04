package com.geekvigarista.scrummanager.client.telas.cadastros.produto;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProdutoConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoView;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class CadastroProdutoPresenter extends Presenter<CadProdutoView, CadProdutoProxy>
{
	public interface CadProdutoView extends View
	{
		TextBox descricao();
		
		Anchor cancelar();
		
		Button salvar();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addprod)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface CadProdutoProxy extends ProxyPlace<CadastroProdutoPresenter>
	{
		
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	private final IProdutoConverter converter;
	private Produto produto;
	
	@Inject
	public CadastroProdutoPresenter(EventBus eventBus, CadProdutoView view, CadProdutoProxy proxy, final PlaceManager placeManager,
			IProdutoConverter converter, final DispatchAsync dispatch)
	{
		super(eventBus, view, proxy);
		this.converter = converter;
		this.dispatch = dispatch;
		this.placeManager = placeManager;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	public Produto getProduto()
	{
		return produto;
	}
	
	public void setProduto(Produto produto)
	{
		this.produto = produto;
		converter.updateView(produto, getView());
	}
}
