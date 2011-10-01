package com.geekvigarista.scrummanager.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoginAuthenticatedEventHandler extends EventHandler
{
	void onLogin(LoginAuthenticateEvent loginAuthenticateEvent);
}
