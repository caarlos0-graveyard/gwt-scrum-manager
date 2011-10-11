package com.geekvigarista.scrummanager.client.telas.inicio.events.updatesearchinput;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateSearchBoxValueEvent extends GwtEvent<UpdateSearchBoxValueEventHandler>
{
	
	private static final Type<UpdateSearchBoxValueEventHandler> TYPE = new Type<UpdateSearchBoxValueEventHandler>();
	
	public static Type<UpdateSearchBoxValueEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, String param)
	{
		eventBus.fireEvent(new UpdateSearchBoxValueEvent(param));
	}
	
	private String param;
	
	public UpdateSearchBoxValueEvent(String param)
	{
		super();
		this.param = param;
	}
	
	public String getParam()
	{
		return param;
	}
	
	public void setParam(String param)
	{
		this.param = param;
	}
	
	@Override
	protected void dispatch(UpdateSearchBoxValueEventHandler handler)
	{
		handler.onUpdateSearchBoxValue(this);
	}
	
	@Override
	public Type<UpdateSearchBoxValueEventHandler> getAssociatedType()
	{
		return getType();
	}
}
