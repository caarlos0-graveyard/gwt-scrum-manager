package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;

public class DefaultDialogBox extends DialogBox
{
	
	public DefaultDialogBox()
	{
		super();
		super.setWidth("100px");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		setAutoHideEnabled(true);
	}
	
	@Override
	public void show()
	{
		super.show();
		center();
	}
	
	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent event)
	{
		super.onPreviewNativeEvent(event);
		switch(event.getTypeInt())
		{
			case Event.ONKEYDOWN:
				if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE)
				{
					hide();
				}
				break;
		}
	}
}
