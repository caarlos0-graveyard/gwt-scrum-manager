package com.geekvigarista.scrummanager.client.telas.inicio.mainmenu;

import com.geekvigarista.scrummanager.client.telas.inicio.mainmenu.MainMenuPresenter.MainMenuView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainMenu extends ViewImpl implements MainMenuView
{
	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);
	@UiField
	MenuItem projeto;
	@UiField
	MenuItem produto;
	@UiField
	MenuItem usuario;
	@UiField
	MenuItem stakeholder;
	@UiField
	MenuItem sair;
	@UiField
	MenuItem inicio;
	@UiField
	MenuItem novo;
	
	Widget widget;
	
	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu>
	{
	}
	
	public MainMenu()
	{
		widget = uiBinder.createAndBindUi(this).asWidget();
	}
	
	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	@Override
	public MenuItem usuario()
	{
		return usuario;
	}
	
	@Override
	public MenuItem stakeholder()
	{
		return stakeholder;
	}
	
	@Override
	public MenuItem projeto()
	{
		return projeto;
	}
	
	@Override
	public MenuItem produto()
	{
		return produto;
	}
	
	@Override
	public MenuItem inicio()
	{
		return inicio;
	}
	
	@Override
	public MenuItem sair()
	{
		return sair;
	}
	
	@Override
	public MenuItem novo()
	{
		return novo;
	}
}
