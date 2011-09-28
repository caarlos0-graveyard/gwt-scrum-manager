package com.geekvigarista.scrummanager.client.telas.inicio.home;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProjetoCellFactory
{
	
	private DateTimeFormat dtf;
	private CellList<Projeto> projetos;
	private ListDataProvider<Projeto> dataProvider;
	private ProvidesKey<Projeto> KEY_PROVIDER;
	private SingleSelectionModel<Projeto> selectionModel;
	
	public ProjetoCellFactory()
	{
		KEY_PROVIDER = new ProvidesKey<Projeto>()
		{
			@Override
			public Object getKey(Projeto item)
			{
				return (item == null) ? null : item.getId();
			}
		};
		
		selectionModel = new SingleSelectionModel<Projeto>();
		dataProvider = new ListDataProvider<Projeto>();
		Cell<Projeto> cell = new ProjetoCell();
		projetos = new CellList<Projeto>(cell, KEY_PROVIDER);
		dataProvider.addDataDisplay(projetos);
		projetos.setSelectionModel(selectionModel);
		projetos.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		projetos.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	}
	
	private class ProjetoCell extends AbstractCell<Projeto>
	{
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Projeto value, SafeHtmlBuilder sb)
		{
			sb.appendHtmlConstant("<span class=\"titulo2\"");
			sb.appendEscaped(value.getNome());
			sb.appendHtmlConstant("</span><br />");
			sb.appendEscaped(dtf.format(value.getDataInicio()));
			sb.appendEscaped(" - ").appendEscaped(dtf.format(value.getDataFim()));
			// TODO - mostrar papel dele nesse projeto
		}
	}

	public CellList<Projeto> getProjetos()
	{
		return projetos;
	}

	public void setProjetos(CellList<Projeto> projetos)
	{
		this.projetos = projetos;
	}

	public ListDataProvider<Projeto> getDataProvider()
	{
		return dataProvider;
	}

	public void setDataProvider(ListDataProvider<Projeto> dataProvider)
	{
		this.dataProvider = dataProvider;
	}

	public ProvidesKey<Projeto> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}

	public void setKEY_PROVIDER(ProvidesKey<Projeto> kEY_PROVIDER)
	{
		KEY_PROVIDER = kEY_PROVIDER;
	}

	public SingleSelectionModel<Projeto> getSelectionModel()
	{
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<Projeto> selectionModel)
	{
		this.selectionModel = selectionModel;
	}

	public void setData(List<Projeto> projs)
	{
		dataProvider.setList(projs);
	}
}
