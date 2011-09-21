package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.user.client.ui.ListBox;

public class DefaultListBox extends ListBox
{
	{
		DefaultComponentsResources.css.ensureInjected();
		addStyleName(DefaultComponentsResources.css.listWidth());
	}
}
