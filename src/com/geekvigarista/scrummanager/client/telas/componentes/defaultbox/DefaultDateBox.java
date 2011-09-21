package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.user.datepicker.client.DateBox;

public class DefaultDateBox extends DateBox
{
	{
		DefaultComponentsResources.css.ensureInjected();
		addStyleName(DefaultComponentsResources.css.inputWidth());
	}
}
