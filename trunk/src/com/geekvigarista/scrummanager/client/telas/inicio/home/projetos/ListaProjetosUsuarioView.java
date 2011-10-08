package com.geekvigarista.scrummanager.client.telas.inicio.home.projetos;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter.ListaProjetosView;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class ListaProjetosUsuarioView extends ViewImpl implements ListaProjetosView
{
	
	private static ListaProjetosUsuario uiBinder = GWT.create(ListaProjetosUsuario.class);
	
	interface ListaProjetosUsuario extends UiBinder<Widget, ListaProjetosUsuarioView>
	{
	}
	
	private ProjetoCellFactory factory;
	
	@UiField
	CellList<ProjetoStakeholderDTO> cellListProjetos;
	
	@UiFactory
	CellList<ProjetoStakeholderDTO> buildCellListProjetos()
	{
		factory = new ProjetoCellFactory();
		return factory.getProjetos();
	}
	
	Widget w;
	
	public ListaProjetosUsuarioView()
	{
		super();
		w = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public void setProjetos(List<ProjetoStakeholderDTO> projetos)
	{
		factory.setData(projetos);
	}

	@Override
	public ProjetoCellFactory factory()
	{
		return factory;
	}
}
