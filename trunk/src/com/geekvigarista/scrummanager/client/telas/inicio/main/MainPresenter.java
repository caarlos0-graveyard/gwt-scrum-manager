package com.geekvigarista.scrummanager.client.telas.inicio.main;

import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter.HomeProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter.HomeView;
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

public class MainPresenter extends Presenter<HomeView, HomeProxy>
{
	/**
	 * Child presenters can fire a RevealContentEvent with TYPE_SetMainContent to set themselves as children of this presenter.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
	
	@ProxyStandard
	public interface HomeProxy extends Proxy<MainPresenter>
	{
	}
	
	public interface HomeView extends View
	{
	}
	
	@Inject
	public MainPresenter(final EventBus eventBus, final HomeView view, final HomeProxy proxy)//, ClientPlaceManager placeManager, DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
	}
}
