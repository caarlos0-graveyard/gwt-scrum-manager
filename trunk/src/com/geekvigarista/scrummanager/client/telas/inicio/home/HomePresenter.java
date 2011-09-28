package com.geekvigarista.scrummanager.client.telas.inicio.home;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class HomePresenter extends Presenter<HomePresenter.HomeView, HomePresenter.HomeProxy>
{
	public interface HomeView extends View
	{
		void setProjetos(List<Projeto> projetos);
		
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.home)
	public interface HomeProxy extends ProxyPlace<HomePresenter>
	{
	}
	
	@Inject
	public HomePresenter(final EventBus eventBus, final HomeView view, final HomeProxy proxy)
	{
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
}
