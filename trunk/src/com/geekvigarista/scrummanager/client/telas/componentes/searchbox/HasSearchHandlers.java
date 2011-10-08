package com.geekvigarista.scrummanager.client.telas.componentes.searchbox;

import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events.SearchEventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasSearchHandlers extends HasHandlers
{
	/**
	 * Adiciona um {@link SearchEvent} handler.
	 * 
	 * @param handler
	 *            o handler de busca
	 * @return {@link HandlerRegistration} usado para remover esse handler
	 */
	HandlerRegistration addSearchHandler(SearchEventHandler handler);
	
}
