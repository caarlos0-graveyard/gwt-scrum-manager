package com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainMenu extends Composite
{
	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);
	
	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu>
	{
	}
	
	public MainMenu()
	{
		System.out.println("MainMenu.MainMenu()");
		initWidget(uiBinder.createAndBindUi(this).asWidget());
	}
	
}
