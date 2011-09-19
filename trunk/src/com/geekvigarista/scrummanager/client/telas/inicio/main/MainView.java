package com.geekvigarista.scrummanager.client.telas.inicio.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainView extends ViewImpl implements MainPresenter.MainView
{
	private static MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);
	
	interface MainPageViewUiBinder extends UiBinder<Widget, MainView>
	{
	}
	
	public final Widget widget;
	
	@UiField
	FlowPanel mainContentPanel;
	
	public MainView()
	{
		widget = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content)
	{
		if(slot == MainPresenter.TYPE_SetMainContent)
		{
			setMainContent(content);
		}
		else
		{
			super.setInSlot(slot, content);
		}
	}
	
	private void setMainContent(Widget content)
	{
		mainContentPanel.clear();
		
		if(content != null)
		{
			mainContentPanel.add(content);
		}
	}
	
}
