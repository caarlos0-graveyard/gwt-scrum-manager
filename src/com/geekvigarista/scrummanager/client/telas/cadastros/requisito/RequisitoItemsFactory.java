package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * 
 * @author caarlos0
 * 
 */
public class RequisitoItemsFactory
{
	
	private CellList<Requisito> requisitos;
	private ListDataProvider<Requisito> dataProvider;
	private ProvidesKey<Requisito> KEY_PROVIDER;
	private SingleSelectionModel<Requisito> selectionModel;
	
	public RequisitoItemsFactory()
	{
		KEY_PROVIDER = new ProvidesKey<Requisito>()
		{
			@Override
			public Object getKey(Requisito item)
			{
				return (item == null) ? null : item.getId();
			}
		};
		
		selectionModel = new SingleSelectionModel<Requisito>();
		dataProvider = new ListDataProvider<Requisito>();
		RequisitoCell cell = new RequisitoCell();
		requisitos = new CellList<Requisito>(cell, KEY_PROVIDER);
		dataProvider.addDataDisplay(requisitos);
		requisitos.setSelectionModel(selectionModel);
		requisitos.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		requisitos.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	}
	
	private class RequisitoCell extends AbstractCell<Requisito>
	{
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Requisito value, SafeHtmlBuilder sb)
		{
			if(value != null)
			{
				sb.appendEscaped(value.getTitulo()).appendEscaped(" (").append(value.getTempoEstimado()).appendEscaped(" hrs)");
			}
		}
	}
	
	public CellList<Requisito> getRequisitos()
	{
		return requisitos;
	}
	
	public void setRequisitos(CellList<Requisito> requisitos)
	{
		this.requisitos = requisitos;
	}
	
	public ListDataProvider<Requisito> getDataProvider()
	{
		return dataProvider;
	}
	
	public void setDataProvider(ListDataProvider<Requisito> dataProvider)
	{
		this.dataProvider = dataProvider;
	}
	
	public ProvidesKey<Requisito> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}
	
	public void setKEY_PROVIDER(ProvidesKey<Requisito> kEY_PROVIDER)
	{
		KEY_PROVIDER = kEY_PROVIDER;
	}
	
	public SingleSelectionModel<Requisito> getSelectionModel()
	{
		return selectionModel;
	}
	
	public void setSelectionModel(SingleSelectionModel<Requisito> selectionModel)
	{
		this.selectionModel = selectionModel;
	}
}
