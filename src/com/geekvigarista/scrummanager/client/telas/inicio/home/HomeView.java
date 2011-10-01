package com.geekvigarista.scrummanager.client.telas.inicio.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.HomeView
{
	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);
	
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView>
	{
	}
	
	@UiField
	SimplePanel panelProjetos;
	
	@UiField
	SimplePanel panelQuadro;
	
	@UiField
	HorizontalSplitPanel splitPanel;
	
	private final Widget widget;
	
	public HomeView()
	{
		widget = uiBinder.createAndBindUi(this);
		splitPanel.setHeight(Window.getClientHeight() + "px");
	}
	
	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content)
	{
		if(slot == HomePresenter.TYPE_SetProjetosContent)
		{
			setProjetos(content);
		}
		else if(slot == HomePresenter.TYPE_SetQuadroScrumContent)
		{
			setQuadro(content);
		}
		else
		{
			super.setInSlot(slot, content);
		}
	}
	
	private void setProjetos(Widget content)
	{
		panelProjetos.clear();
		if(content != null)
		{
			panelProjetos.setWidget(content);
		}
	}
	
	private void setQuadro(Widget content)
	{
		panelQuadro.clear();
		if(content != null)
		{
			panelQuadro.setWidget(content);
		}
	}
	
}
