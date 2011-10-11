package com.geekvigarista.scrummanager.client.telas.inicio.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.HomeView, ResizeHandler
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
	HorizontalSplitPanel splitPanel; //FIXME um dia
	
	private final Widget widget;
	
	public HomeView()
	{
		widget = uiBinder.createAndBindUi(this);
		Window.addResizeHandler(this);
		adjustPanelHeight();
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
	
	@Override
	public void onResize(ResizeEvent event)
	{
		adjustPanelHeight();
	}
	
	void adjustPanelHeight()
	{
		splitPanel.setHeight(Window.getClientHeight() - 93 + "px");
	}
	
}
