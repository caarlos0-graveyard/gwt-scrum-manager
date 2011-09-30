package com.geekvigarista.scrummanager.client.telas.erros;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.erros.Error404Presenter.Erro404Proxy;
import com.geekvigarista.scrummanager.client.telas.erros.Error404Presenter.Erro404View;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class Error404Presenter extends Presenter<Erro404View, Erro404Proxy>
{
	@ProxyStandard
	@NameToken(NameTokens.erro404)
	public interface Erro404Proxy extends ProxyPlace<Error404Presenter>
	{
		
	}
	
	public interface Erro404View extends View
	{
		
	}
	
	@Inject
	public Error404Presenter(EventBus eventBus, Erro404View view, Erro404Proxy proxy)
	{
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealRootContentEvent.fire(this, this);
	}
}
