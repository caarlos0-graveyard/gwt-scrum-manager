package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.user.client.ui.DialogBox;

public class DefaultDialogBox extends DialogBox
{
	
	public DefaultDialogBox()
	{
		super();
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
	
}
