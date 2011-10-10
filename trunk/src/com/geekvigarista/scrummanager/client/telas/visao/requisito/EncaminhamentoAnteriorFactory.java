package com.geekvigarista.scrummanager.client.telas.visao.requisito;

import java.util.Date;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

public class EncaminhamentoAnteriorFactory
{
	private CellTable<Encaminhamento> cellTable;
	private ListDataProvider<Encaminhamento> dataProvider;
	private ProvidesKey<Encaminhamento> KEY_PROVIDER;
	private SingleSelectionModel<Encaminhamento> selectionModel;
	
	public EncaminhamentoAnteriorFactory()
	{
		KEY_PROVIDER = new ProvidesKey<Encaminhamento>()
		{
			@Override
			public Object getKey(Encaminhamento item)
			{
				return (item == null) ? null : item.getId();
			}
		};
		
		selectionModel = new SingleSelectionModel<Encaminhamento>();
		dataProvider = new ListDataProvider<Encaminhamento>();
		cellTable = new CellTable<Encaminhamento>(KEY_PROVIDER);
		dataProvider.addDataDisplay(cellTable);
		cellTable.setSelectionModel(selectionModel);
		cellTable.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		initColumns();
	}
	
	private void initColumns()
	{
		//		Column<Encaminhamento, Boolean> checkColumn = new Column<Encaminhamento, Boolean>(new CheckboxCell(true, false))
		//		{
		//			@Override
		//			public Boolean getValue(Encaminhamento object)
		//			{
		//				return selectionModel.isSelected(object);
		//			}
		//		};
		//		
		Column<Encaminhamento, String> stakeholderColumn = new Column<Encaminhamento, String>(new TextCell())
		{
			@Override
			public String getValue(Encaminhamento object)
			{
				if(object.getStakeholder() != null)
				{
					return object.getStakeholder().getNome() + " (" + object.getStakeholder().getPapel() + ")";
				}
				else
				{
					return "Sem Stakeholder";
				}
			}
		};
		Column<Encaminhamento, Date> dataColumn = new Column<Encaminhamento, Date>(new DateCell())
		{
			@Override
			public Date getValue(Encaminhamento object)
			{
				return object.getData();
			}
		};
		
		Column<Encaminhamento, String> tempoColumn = new Column<Encaminhamento, String>(new TextCell())
		{
			@Override
			public String getValue(Encaminhamento object)
			{
				return object.getTempoGasto() + "";
			}
		};
		Column<Encaminhamento, String> statusColumn = new Column<Encaminhamento, String>(new TextCell())
		{
			@Override
			public String getValue(Encaminhamento object)
			{
				return object.getStatus().desc();
			}
		};
		
		//		cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		//		cellTable.setColumnWidth(checkColumn, 40, Unit.PX);
		cellTable.addColumn(dataColumn, Mensagem.get.data());
		cellTable.addColumn(stakeholderColumn, Mensagem.get.stakeholders());
		cellTable.addColumn(tempoColumn, Mensagem.get.tempoGasto());
		cellTable.addColumn(statusColumn, Mensagem.get.status());
		// TODO descricao
	}
	
	public CellTable<Encaminhamento> getCellTable()
	{
		return cellTable;
	}
	
	public ListDataProvider<Encaminhamento> getDataProvider()
	{
		return dataProvider;
	}
	
	public ProvidesKey<Encaminhamento> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}
	
	public SingleSelectionModel<Encaminhamento> getSelectionModel()
	{
		return selectionModel;
	}
}
