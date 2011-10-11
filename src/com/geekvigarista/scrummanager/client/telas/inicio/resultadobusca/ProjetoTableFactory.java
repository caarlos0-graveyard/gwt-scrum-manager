package com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca;

import java.util.Date;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProjetoTableFactory
{
	private final CellTable<Projeto> cellTable;
	private final ListDataProvider<Projeto> dataProvider;
	private final ProvidesKey<Projeto> KEY_PROVIDER;
	private final SingleSelectionModel<Projeto> selectionModel;
	
	public ProjetoTableFactory()
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
		cellTable = new CellTable<Projeto>(KEY_PROVIDER);
		dataProvider.addDataDisplay(cellTable);
		cellTable.setSelectionModel(selectionModel);
		cellTable.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		initColumns();
	}
	
	public void initColumns()
	{
		Column<Projeto, String> nomeColumn = new Column<Projeto, String>(new TextCell())
		{
			@Override
			public String getValue(Projeto object)
			{
				return object.getNome() != null && !object.getNome().trim().isEmpty() ? object.getNome() : Mensagem.get.naoAssociado();
			}
		};
		
		Column<Projeto, String> produtoColumn = new Column<Projeto, String>(new TextCell())
		{
			@Override
			public String getValue(Projeto object)
			{
				return object.getProduto() != null ? object.getProduto().getDescricao() : "-";
			}
		};
		
		Column<Projeto, Date> dataInicioColumn = new Column<Projeto, Date>(new DateCell(DateTimeFormat.getFormat("dd/MM/yyyy")))
		{
			@Override
			public Date getValue(Projeto object)
			{
				return object.getDataInicio();
			}
		};
		
		Column<Projeto, Date> dataFimColumn = new Column<Projeto, Date>(new DateCell(DateTimeFormat.getFormat("dd/MM/yyyy")))
		{
			@Override
			public Date getValue(Projeto object)
			{
				return object.getDataFim();
			}
		};
		
		Column<Projeto, Number> quantidadeReqsColumn = new Column<Projeto, Number>(new NumberCell())
		{
			@Override
			public Integer getValue(Projeto object)
			{
				return object.getRequisitos().size();
			}
		};
		
		Column<Projeto, Number> totalHorasEstimadasColumn = new Column<Projeto, Number>(new NumberCell())
		{
			@Override
			public Integer getValue(Projeto object)
			{
				int total = 0;
				for(Requisito s : object.getRequisitos())
				{
					total += s.getTempoEstimado();
				}
				return total;
			}
		};
		
		Column<Projeto, Number> totalHorasTrabalhadasColumn = new Column<Projeto, Number>(new NumberCell())
		{
			@Override
			public Integer getValue(Projeto object)
			{
				int total = 0;
				for(Requisito s : object.getRequisitos())
				{
					total += s.getTempoTotal();
				}
				return total;
			}
		};
		
		cellTable.addColumn(nomeColumn, Mensagem.get.nome());
		cellTable.addColumn(produtoColumn, Mensagem.get.produto());
		cellTable.addColumn(dataInicioColumn, Mensagem.get.dataInicio());
		cellTable.addColumn(dataFimColumn, Mensagem.get.dataFim());
		cellTable.addColumn(totalHorasEstimadasColumn, Mensagem.get.tempoEstimado());
		cellTable.addColumn(totalHorasTrabalhadasColumn, Mensagem.get.tempoGasto());
		cellTable.addColumn(quantidadeReqsColumn, "Qtde " + Mensagem.get.requisitos());
		
	}
	
	public CellTable<Projeto> getTabela()
	{
		return cellTable;
	}
	
	public ListDataProvider<Projeto> getDataProvider()
	{
		return dataProvider;
	}
	
	public ProvidesKey<Projeto> getKEY_PROVIDER()
	{
		return KEY_PROVIDER;
	}
	
	public SingleSelectionModel<Projeto> getSelectionModel()
	{
		return selectionModel;
	}
}
