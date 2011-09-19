package com.geekvigarista.scrummanager.client.telas.inicio.home;

import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter.MyView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements MyView
{
	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);
	
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView>
	{
	}
	
	private final Widget widget;
	
	public HomeView()
	{
		widget = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return widget;
	}
}
