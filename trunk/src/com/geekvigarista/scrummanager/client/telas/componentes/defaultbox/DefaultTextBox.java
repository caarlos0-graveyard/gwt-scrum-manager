package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.user.client.ui.TextBox;

public class DefaultTextBox extends TextBox
{
	{
		DefaultComponentsResources.css.ensureInjected();
		addStyleName(DefaultComponentsResources.css.inputWidth());
	}
}
