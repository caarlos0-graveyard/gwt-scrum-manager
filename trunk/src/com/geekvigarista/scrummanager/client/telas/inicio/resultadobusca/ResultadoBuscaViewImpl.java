package com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.componentes.showmorepagepanel.ShowMorePagerPanel;
import com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca.ResultadoBuscaPresenter.ResultadoBuscaView;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.mvp.client.ViewImpl;

public class ResultadoBuscaViewImpl extends ViewImpl implements ResultadoBuscaView
{
	
	private static ResultadoBuscaViewImplUiBinder uiBinder = GWT.create(ResultadoBuscaViewImplUiBinder.class);
	
	interface ResultadoBuscaViewImplUiBinder extends UiBinder<Widget, ResultadoBuscaViewImpl>
	{
	}
	
	@UiField
	ShowMorePagerPanel pager = new ShowMorePagerPanel();
	
	CellTable<Projeto> tabelaProjeto;
	
	private Widget w;
	
	private ProjetoTableFactory factory = new ProjetoTableFactory();
	
	public ResultadoBuscaViewImpl()
	{
		w = uiBinder.createAndBindUi(this);
		
		tabelaProjeto = factory.getTabela();
		pager.setDisplay(tabelaProjeto);
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public void setData(List<Projeto> lista)
	{
		factory.getDataProvider().setList(lista);
		factory.getDataProvider().refresh();
	}
	
	@Override
	public SingleSelectionModel<Projeto> geetSelectionModel()
	{
		return factory.getSelectionModel();
	}
	
}
