package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
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
		Column<Stakeholder, String> nomeColumn = new Column<Stakeholder, String>(new TextCell())
		{
			@Override
			public String getValue(Stakeholder object)
			{
				return object.getNome() != null && !object.getNome().trim().isEmpty() ? object.getNome() : Mensagem.get.naoAssociado();
			}
		};
		
		Column<Stakeholder, String> userColumn = new Column<Stakeholder, String>(new TextCell())
		{
			@Override
			public String getValue(Stakeholder object)
			{
				return (object.getUsuario() != null ? (object.getUsuario().getNome() != null && !object.getUsuario().getNome().trim().isEmpty() ? object
						.getUsuario().getNome() : Mensagem.get.naoAssociado())
						: Mensagem.get.naoAssociado());
			}
		};
		
		Column<Stakeholder, String> papelColumn = new Column<Stakeholder, String>(new TextCell())
		{
			@Override
			public String getValue(Stakeholder object)
			{
				return object.getPapel() != null ? object.getPapel().desc() : Mensagem.get.naoAssociado();
			}
		};
		
		cellTable.addColumn(nomeColumn, Mensagem.get.nome());
		cellTable.addColumn(userColumn, Mensagem.get.usuario());
		cellTable.addColumn(papelColumn, Mensagem.get.papelStakeholder());
		
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
