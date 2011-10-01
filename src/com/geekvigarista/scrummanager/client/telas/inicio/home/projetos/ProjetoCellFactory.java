package com.geekvigarista.scrummanager.client.telas.inicio.home.projetos;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
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
	
	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yyyy");
	private CellList<ProjetoStakeholderDTO> projetos;
	private ListDataProvider<ProjetoStakeholderDTO> dataProvider;
	private ProvidesKey<ProjetoStakeholderDTO> KEY_PROVIDER;
	private SingleSelectionModel<ProjetoStakeholderDTO> selectionModel;
	
	public ProjetoCellFactory()
	{
		KEY_PROVIDER = new ProvidesKey<ProjetoStakeholderDTO>()
		{
			@Override
			public Object getKey(ProjetoStakeholderDTO item)
			{
				return (item == null) ? null : item.getProjeto().getId();
			}
		};
		
		selectionModel = new SingleSelectionModel<ProjetoStakeholderDTO>();
		dataProvider = new ListDataProvider<ProjetoStakeholderDTO>();
		Cell<ProjetoStakeholderDTO> cell = new ProjetoCell();
		projetos = new CellList<ProjetoStakeholderDTO>(cell, KEY_PROVIDER);
		dataProvider.addDataDisplay(projetos);
		projetos.setSelectionModel(selectionModel);
		projetos.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		projetos.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	}
	
	private class ProjetoCell extends AbstractCell<ProjetoStakeholderDTO>
	{
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, ProjetoStakeholderDTO value, SafeHtmlBuilder sb)
		{
			if(value == null)
				return;
			Projeto p = value.getProjeto();
			Stakeholder s = value.getStakeholder();
			sb.appendHtmlConstant("<span class=\"tituloCell\">");
			sb.appendEscaped(p.getNome());
			sb.appendHtmlConstant("</span><br />");
			sb.appendEscaped(dtf.format(p.getDataInicio()));
			sb.appendEscaped(" - ").appendEscaped(dtf.format(p.getDataFim()));
			sb.appendHtmlConstant("<br />");
			sb.appendEscaped("Papel: ").appendEscaped(s.getPapel().desc());
			System.out.println("ProjetoCellFactory.ProjetoCell.render()");
		}
	}
	
	public CellList<ProjetoStakeholderDTO> getProjetos()
	{
		return projetos;
	}
	
	public void setProjetos(CellList<ProjetoStakeholderDTO> projetos)
	{
		this.projetos = projetos;
	}
	
	public ListDataProvider<ProjetoStakeholderDTO> getDataProvider()
	{
		return dataProvider;
	}
	
	public void setDataProvider(ListDataProvider<ProjetoStakeholderDTO> dataProvider)
	{
		this.dataProvider = dataProvider;
	}
	
	public ProvidesKey<ProjetoStakeholderDTO> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}
	
	public void setKEY_PROVIDER(ProvidesKey<ProjetoStakeholderDTO> kEY_PROVIDER)
	{
		KEY_PROVIDER = kEY_PROVIDER;
	}
	
	public SingleSelectionModel<ProjetoStakeholderDTO> getSelectionModel()
	{
		return selectionModel;
	}
	
	public void setSelectionModel(SingleSelectionModel<ProjetoStakeholderDTO> selectionModel)
	{
		this.selectionModel = selectionModel;
	}
	
	public void setData(List<ProjetoStakeholderDTO> projs)
	{
		if(projs == null)
		{
			projs = new ArrayList<ProjetoStakeholderDTO>();
		}
		dataProvider.setList(projs);
		dataProvider.refresh();
	}
}
