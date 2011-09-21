package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddProjetoPresenter extends Presenter<AddProjetoPresenter.AddProjetoView, AddProjetoPresenter.AddProjetoProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addproj)
	public interface AddProjetoProxy extends ProxyPlace<AddProjetoPresenter>
	{
	}
	
	public interface AddProjetoView extends View
	{
		
	}
	
	private final DispatchAsync dispatch;
	
	@Inject
	public AddProjetoPresenter(final EventBus eventBus, final AddProjetoView view, final AddProjetoProxy proxy, final DispatchAsync dispatch)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
}
