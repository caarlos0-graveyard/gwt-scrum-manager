package com.geekvigarista.scrummanager.client.telas.inicio.topo;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.SearchBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
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
	
	@Inject
	public TopoPresenter(EventBus eventBus, TopoView view, TopoProxy proxy)
	{
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetTopoContent, this);
	}
	
}
