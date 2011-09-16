package com.geekvigarista.scrummanager.client.place;

import com.geekvigarista.scrummanager.client.presenter.CadastroUsuarioPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class ClientPlaceManager extends PlaceManagerImpl
{
	@Inject
	public ClientPlaceManager(final EventBus eventBus, final TokenFormatter tokenFormatter)
	{
		super(eventBus, tokenFormatter);
		//		this.defaultPlaceRequest = new PlaceRequest(defaultPlaceNameToken);
	}
	
	@Override
	public void revealDefaultPlace()
	{
		//		revealPlace(defaultPlaceRequest, false);
		revealPlace(new PlaceRequest(CadastroUsuarioPresenter.nameToken), false);
	}
}
