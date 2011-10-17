package com.geekvigarista.scrummanager.client.telas.componentes.searchbox.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class SearchEvent extends GwtEvent<SearchEventHandler>
{
	private static final Type<SearchEventHandler> TYPE = new Type<SearchEventHandler>();
	
	public static Type<SearchEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus)
	{
		eventBus.fireEvent(new SearchEvent());
	}
	
	public SearchEvent(){}
	
	@Override
	protected void dispatch(SearchEventHandler handler)
	{
		handler.onSearch(this);
	}
	
	@Override
	public Type<SearchEventHandler> getAssociatedType()
	{
		return getType();
	}
}
