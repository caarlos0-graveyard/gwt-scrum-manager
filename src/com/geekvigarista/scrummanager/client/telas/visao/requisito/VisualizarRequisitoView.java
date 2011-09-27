package com.geekvigarista.scrummanager.client.telas.visao.requisito;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.user.cellview.client.CellTable;

public class VisualizarRequisitoView extends ViewImpl implements VisualizarRequisitoPresenter.VisReqView
{
	
	private static VisualizarRequisitoViewUiBinder uiBinder = GWT.create(VisualizarRequisitoViewUiBinder.class);
	
	interface VisualizarRequisitoViewUiBinder extends UiBinder<Widget, VisualizarRequisitoView>
	{
	}
	
	/*
	 * atributos
	 */
	@UiField
	Label titulo;
	
	@UiField
	Label prioridade;
	
	@UiField
	DateLabel dataCadastro;
	
	@UiField
	Label tempoEstimado;
	
	@UiField
	Label tempoGasto;
	
	@UiField
	Label descricao;
	
	@UiField
	HTMLPanel conteudo;
	
	@UiField
	//(provided = true)
	CellTable<Encaminhamento> encaminhamentosAnteriores = null;
	
	private EncaminhamentoAnteriorFactory factory;
	
	@UiFactory
	public CellTable<Encaminhamento> buildCellTable()
	{
		factory = new EncaminhamentoAnteriorFactory();
		return factory.getCellTable();
	}
	
	public VisualizarRequisitoView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public Label titulo()
	{
		return titulo;
	}
	
	@Override
	public Label prioridade()
	{
		return prioridade;
	}
	
	@Override
	public DateLabel dataCadastro()
	{
		return dataCadastro;
	}
	
	@Override
	public Label tempoEstimado()
	{
		return tempoEstimado;
	}
	
	@Override
	public Label tempoGasto()
	{
		return tempoGasto;
	}
	
	@Override
	public Label descricao()
	{
		return descricao;
	}
	
	@Override
	public CellTable<Encaminhamento> encaminhamentosAnteriores()
	{
		return encaminhamentosAnteriores;
	}
	
	@Override
	public void setData(List<Encaminhamento> encaminhamentos)
	{
		if(factory == null)
		{
			factory = new EncaminhamentoAnteriorFactory();
		}
		
		factory.getDataProvider().setList(encaminhamentos);
	}
	
}
