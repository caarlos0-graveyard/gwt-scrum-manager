package com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface usada para execução do SearchEvent.
 * 
 * @author caarlos0
 */
public interface SearchEventHandler extends EventHandler
{
	void onSearch(SearchEvent event);
}
