package com.geekvigarista.scrummanager.client.telas.inicio.home;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy>
{
	public interface MyView extends View
	{
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.home)
	public interface MyProxy extends ProxyPlace<HomePresenter>
	{
	}
	
	@Inject
	public HomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy)
	{
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
}
