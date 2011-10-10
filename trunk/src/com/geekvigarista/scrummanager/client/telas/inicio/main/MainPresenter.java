package com.geekvigarista.scrummanager.client.telas.inicio.main;

import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter.MainProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter.MainView;
import com.geekvigarista.scrummanager.client.telas.inicio.mainmenu.MainMenuPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.topo.TopoPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPresenter extends Presenter<MainView, MainProxy>
{
	/**
	 * Child presenters can fire a RevealContentEvent with TYPE_SetMainContent to set themselves as children of this presenter.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTopoContent = new Type<RevealContentHandler<?>>();
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMenuContent = new Type<RevealContentHandler<?>>();
	
	@ProxyStandard
	public interface MainProxy extends Proxy<MainPresenter>
	{
	}
	
	public interface MainView extends View
	{
	}
	
	private final MainMenuPresenter mainmenu;
	private final TopoPresenter topo;
	
	@Inject
	public MainPresenter(final EventBus eventBus, final MainView view, final MainProxy proxy, final MainMenuPresenter mainmenu,
			final TopoPresenter topo)//, ClientPlaceManager placeManager, DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.mainmenu = mainmenu;
		this.topo = topo;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		setInSlot(TYPE_SetMenuContent, mainmenu);
		setInSlot(TYPE_SetTopoContent, topo);
	}
}
