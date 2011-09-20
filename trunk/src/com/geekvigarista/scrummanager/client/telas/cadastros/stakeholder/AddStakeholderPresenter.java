package com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddStakeholderPresenter extends Presenter<AddStakeholderPresenter.AddStakeholderView, AddStakeholderPresenter.AddStakeholderProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addstak)
	public interface AddStakeholderProxy extends ProxyPlace<AddStakeholderPresenter>
	{
	}
	
	public interface AddStakeholderView extends View
	{
		ListBox getUsuarios();
		
		ListBox getProjetos();
		
		ListBox getPapeis();
		
		HasValue<String> getNome();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
	}
	
	private final DispatchAsync dispatcher;
	
	@Inject
	public AddStakeholderPresenter(final EventBus eventBus, final AddStakeholderView view, final AddStakeholderProxy proxy,
			final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
}
