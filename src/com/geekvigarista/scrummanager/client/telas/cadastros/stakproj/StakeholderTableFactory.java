package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;

public class StakeholderTableFactory
{
	private final CellTable<Stakeholder> cellTable;
	private final ListDataProvider<Stakeholder> dataProvider;
	private final ProvidesKey<Stakeholder> KEY_PROVIDER;
	private final MultiSelectionModel<Stakeholder> selectionModel;
	
	public StakeholderTableFactory()
	{
		KEY_PROVIDER = new ProvidesKey<Stakeholder>()
		{
			@Override
			public Object getKey(Stakeholder item)
			{
				return (item == null) ? null : item.getId();
			}
		};
		
		selectionModel = new MultiSelectionModel<Stakeholder>();
		dataProvider = new ListDataProvider<Stakeholder>();
		cellTable = new CellTable<Stakeholder>(KEY_PROVIDER);
		dataProvider.addDataDisplay(cellTable);
		cellTable.setSelectionModel(selectionModel);
		cellTable.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		initColumns();
	}
	
	public void initColumns()
	{
		Column<Stakeholder, Boolean> checkColumn = new Column<Stakeholder, Boolean>(new CheckboxCell(true, false))
		{
			@Override
			public Boolean getValue(Stakeholder object)
			{
				return selectionModel.isSelected(object);
			}
		};
		
		Column<Stakeholder, String> nomeColumn = new Column<Stakeholder, String>(new TextCell())
		{
			@Override
			public String getValue(Stakeholder object)
			{
				return object.getNome() + " (" + (object.getUsuario() != null ? object.getUsuario().getLogin() : "null") + ") " + object.getId();
			}
		};
		
		cellTable.addColumn(checkColumn);
		cellTable.addColumn(nomeColumn, "Nome sstakeholder - Usuario");
	}
	
	public CellTable<Stakeholder> getTabela()
	{
		return cellTable;
	}
	
	public ListDataProvider<Stakeholder> getDataProvider()
	{
		return dataProvider;
	}
	
	public ProvidesKey<Stakeholder> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}
	
	public MultiSelectionModel<Stakeholder> getSelectionModel()
	{
		return selectionModel;
	}
}
