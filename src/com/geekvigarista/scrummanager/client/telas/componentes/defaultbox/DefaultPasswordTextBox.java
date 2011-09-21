package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class DefaultPasswordTextBox extends PasswordTextBox
{
	{
		DefaultComponentsResources.css.ensureInjected();
		addStyleName(DefaultComponentsResources.css.inputWidth());
	}
}
