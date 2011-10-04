package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjView;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class AddStakToProjPresenter extends Presenter<AddStakToProjView, AddStakToProjProxy>
{
	public interface AddStakToProjView extends View
	{
	}
	
	public interface AddStakToProjProxy extends ProxyPlace<AddStakToProjPresenter>
	{
		
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	
	@Inject
	public AddStakToProjPresenter(EventBus eventBus, AddStakToProjView view, AddStakToProjProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatch)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
	}
	
	@Override
	protected void revealInParent()
	{
		
	}
}
