package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProjetoConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.cadastros.interfaces.SimpleCadPresenter;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.componentes.defaultbox.DefaultListBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.produto.busca.BuscaTodosProdutosAction;
import com.geekvigarista.scrummanager.shared.commands.produto.busca.BuscarProdutoListResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoResult;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
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

public class AddProjetoPresenter extends SimpleCadPresenter<AddProjetoPresenter.AddProjetoView, AddProjetoPresenter.AddProjetoProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addproj)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface AddProjetoProxy extends ProxyPlace<AddProjetoPresenter>
	{
	}
	
	public interface AddProjetoView extends View
	{
		TextBox getNome();
		
		HasValue<Date> getDtInicio();
		
		HasValue<Date> getDtFim();
		
		//		Button getAddRequisitos();
		//		
		//		Button getAddStakeholders();
		
		DefaultListBox getLBProdutos();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtSalvar();
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	private final IProjetoConverter converter;
	private Projeto projeto;
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@Inject
	public AddProjetoPresenter(final EventBus eventBus, final AddProjetoView view, final AddProjetoProxy proxy, final DispatchAsync dispatch,
			final PlaceManager placeManager, final IProjetoConverter converter)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
		this.converter = converter;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		registerHandler(getView().getBtSalvar().addClickHandler(salvarHandler));
		registerHandler(getView().getNome().addKeyUpHandler(salvarHandler));
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.projid, null);
		
		if(id == null)
		{
			setProjeto(null);
			return;
		}
		
		dispatch.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
			}
		});
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		doReloadProdutos();
		getView().getNome().setFocus(true);
	}
	
	public void doAvancar()
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.addreq).with(Parameters.projid, projeto != null ? projeto.getId() : "null"); // HERE
		placeManager.revealPlace(pr);
	}
	
	public void doReloadProdutos()
	{
		dispatch.execute(new BuscaTodosProdutosAction(), new AbstractCallback<BuscarProdutoListResult>()
		{
			@Override
			public void onSuccess(BuscarProdutoListResult result)
			{
				setProdutos(result.getResponse());
			}
		});
	}
	
	@Override
	public void doSalvar()
	{
		Projeto projetoConvertido = converter.convert(getProjeto(), getView(), getProdutos());
		dispatch.execute(new SalvarProjetoAction(projetoConvertido), new AbstractCallback<SalvarProjetoResult>()
		{
			@Override
			public void onSuccess(SalvarProjetoResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					setProjeto(result.getResponse());
					
					String msg = "Projeto " + result.getResponse().getNome() + " salvo com sucesso";
					new MsgBox(msg, false);
					doAvancar();
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		});
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
		converter.updateView(projeto, getView());
	}
	
	public List<Produto> getProdutos()
	{
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
		for(Produto p : produtos)
		{
			getView().getLBProdutos().addItem(p.getDescricao());
		}
		if(getProjeto() != null && getProjeto().getProduto() != null)
		{
			int index = -1;
			for(Produto p : produtos)
			{
				if(projeto.getProduto().getDescricao() != null &&  p.getDescricao() != null && p.getDescricao().equals(projeto.getProduto().getDescricao()))
				{
					index = produtos.indexOf(p);
				}
			}
			getView().getLBProdutos().setSelectedIndex(index);
		}
	}
	
}
