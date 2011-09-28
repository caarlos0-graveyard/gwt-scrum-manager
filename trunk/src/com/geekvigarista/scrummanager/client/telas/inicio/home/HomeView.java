package com.geekvigarista.scrummanager.client.telas.inicio.home;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.HomeView
{
	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);
	
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView>
	{
	}
	
	
	
	private ProjetoCellFactory factory;
	
	@UiField
	CellList<Projeto> cellListProjetos;
	
	@UiFactory
	CellList<Projeto> buildCellListProjetos()
	{
		factory = new ProjetoCellFactory();
		return factory.getProjetos();
	}
	
	@UiField
	HorizontalPanel panelScrum;

	
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

	@Override
	public void setProjetos(List<Projeto> projetos)
	{
		factory.setData(projetos);
	}
}
