package com.geekvigarista.scrummanager.client.telas.cadastros.produto;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProdutoConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.interfaces.SimpleCadPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoView;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.produto.salvar.SalvarProdutoAction;
import com.geekvigarista.scrummanager.shared.commands.produto.salvar.SalvarProdutoResult;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class CadastroProdutoPresenter extends SimpleCadPresenter<CadProdutoView, CadProdutoProxy>
{
	public interface CadProdutoView extends View
	{
		TextBox descricao();
		
		Anchor cancelar();
		
		Button salvar();
		
		Button novo();
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
	private SalvarHandler salvarhand;
	
	@Inject
	public CadastroProdutoPresenter(EventBus eventBus, CadProdutoView view, CadProdutoProxy proxy, final PlaceManager placeManager,
			IProdutoConverter converter, final DispatchAsync dispatch)
	{
		super(eventBus, view, proxy);
		this.converter = converter;
		this.dispatch = dispatch;
		this.placeManager = placeManager;
		salvarhand = new SalvarHandler();
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
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		getView().descricao().setFocus(true);
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		getView().descricao().addKeyUpHandler(salvarhand);
		getView().salvar().addClickHandler(salvarhand);
		getView().cancelar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doCancelar();
			}
		});
		getView().novo().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doNovo();
			}
		});
	}
	
	private class SalvarHandler implements ClickHandler, KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				doSalvar();
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			doSalvar();
		}
	}

	@Override
	public void doSalvar()
	{
		Produto p = converter.convert(getProduto(), getView());
		dispatch.execute(new SalvarProdutoAction(p), new AbstractCallback<SalvarProdutoResult>()
		{
			@Override
			public void onSuccess(SalvarProdutoResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					String msg = "Produto " + result.getResponse().getDescricao() + " salvo com sucesso";
					new MsgBox(msg, false);
					setProduto(result.getResponse());
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		});
	}

	@Override
	public void doNovo()
	{
		setProduto(new Produto());
	}

	@Override
	public void doCancelar()
	{
		placeManager.revealPlace(new PlaceRequest(NameTokens.home));
	}

	@Override
	public void doExcluir()
	{
		// TODO vai excluir ?
		
	}
}
