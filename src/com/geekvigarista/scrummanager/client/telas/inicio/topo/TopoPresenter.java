package com.geekvigarista.scrummanager.client.telas.inicio.topo;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.SearchBox;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.events.updatesearchinput.UpdateSearchBoxValueEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.updatesearchinput.UpdateSearchBoxValueEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class TopoPresenter extends Presenter<TopoPresenter.TopoView, TopoPresenter.TopoProxy> implements UpdateSearchBoxValueEventHandler
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
	
	private final PlaceManager placemanager;
	
	@Inject
	public TopoPresenter(EventBus eventBus, TopoView view, TopoProxy proxy, final PlaceManager placemanager)
	{
		super(eventBus, view, proxy);
		this.placemanager = placemanager;
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
		placemanager.revealPlace(new PlaceRequest(NameTokens.busca).with("q", parametro));
		//		getEventBus().fireEvent(new BuscarExibirResultadoEvent(parametro));
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetTopoContent, this);
	}
	
	@Override
	@ProxyEvent
	public void onUpdateSearchBoxValue(UpdateSearchBoxValueEvent event)
	{
		getView().buscar().setValue(event.getParam());
	}
	
}
