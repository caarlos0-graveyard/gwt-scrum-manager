package com.geekvigarista.scrummanager.client.place;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class ClientPlaceManager extends PlaceManagerImpl
{
	
	private final PlaceRequest defaultPlaceRequest;
	
	@Inject
	public ClientPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter, @DefaultPlace String defaultNameToken)
	{
		super(eventBus, tokenFormatter);
		this.defaultPlaceRequest = new PlaceRequest(defaultNameToken);
	}
	
	@Override
	public void revealDefaultPlace()
	{
		// Using false as a second parameter ensures that the URL in the browser bar
		// is not updated, so the user is able to leave the application using the
		// browser's back navigation button.
		revealPlace(defaultPlaceRequest, false);
	}
	
	@Override
	public void revealErrorPlace(String invalidHistoryToken)
	{
		System.out.println(invalidHistoryToken);
		revealPlace(new PlaceRequest(NameTokens.erro404));
	}
	
	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken)
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.login).with(Parameters.u, unauthorizedHistoryToken);
		revealPlace(pr);
	}
	
}
