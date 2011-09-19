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
		System.out.println("ClientPlaceManager.revealDefaultPlace()");
		//		revealPlace(new PlaceRequest(NameTokens.home), false);
		revealPlace(defaultPlaceRequest);
	}
	
	@Override
	  public void revealErrorPlace(String invalidHistoryToken) {
	    super.revealErrorPlace(invalidHistoryToken);
	  }
}
