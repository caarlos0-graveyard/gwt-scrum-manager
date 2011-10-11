package com.geekvigarista.scrummanager.client.telas.inicio.topo;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.SearchBox;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoLikeAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoListResult;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class TopoPresenter extends Presenter<TopoPresenter.TopoView, TopoPresenter.TopoProxy>
{
	public interface TopoView extends View
	{
		SearchBox buscar();
	}
	
	@ProxyCodeSplit
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface TopoProxy extends Proxy<TopoPresenter>
	{
		
	}
	
	private final DispatchAsync dispatch;
	private final UsuarioLogadoGatekeeper gatekepper;

	@Inject
	public TopoPresenter(EventBus eventBus, TopoView view, TopoProxy proxy,final DispatchAsync dispatch, final UsuarioLogadoGatekeeper gatekepper)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.gatekepper = gatekepper;
	}
	
	@Override
	public void onBind()
	{
		getView().buscar().addSearchHandler(new SearchEventHandler()
		{
			@Override
			public void onSearch(SearchEvent event)
			{
				doPesquisar();
			}
		});
	}
	
	public void doPesquisar()
	{
		final String parametro = getView().buscar().getValue();
		new AbstractCallback<BuscarProjetoListResult>()
		{
			@Override
			public void onSuccess(BuscarProjetoListResult result)
			{
				for(ProjetoStakeholderDTO p : result.getProjetos())
				{
					System.out.println("FILHO DA PUTA ! " + p.getProjeto().getNome());
				}
			}

			@Override
			protected void callService(AsyncCallback<BuscarProjetoListResult> asyncCallback)
			{
				Usuario usuario = gatekepper.getUsuario();
				System.out.println(usuario.getNome());
				dispatch.execute(new BuscarProjetoLikeAction(parametro, usuario ), asyncCallback);
			}
		}.goDefault();
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetTopoContent, this);
	}
	
}
