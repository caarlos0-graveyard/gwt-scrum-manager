package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjView;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddStakToProjViewImpl extends ViewImpl implements AddStakToProjView
{
	
	private static AddStakToProjViewImplUiBinder uiBinder = GWT.create(AddStakToProjViewImplUiBinder.class);
	
	private StakeholderTableFactory factory = new StakeholderTableFactory();
	
	@UiField
	CellTable<Stakeholder> tabelaStakeholders;
	
	@UiField
	Button btVoltar;
	
	@UiField
	Button btAvancar;
	
	@UiField
	Anchor btCancelar;
	
	Widget w;
	
	@UiFactory
	public CellTable<Stakeholder> buildTabelaStakeholder()
	{
		tabelaStakeholders = new CellTable<Stakeholder>();
		return factory.getTabela();
	}
	
	interface AddStakToProjViewImplUiBinder extends UiBinder<Widget, AddStakToProjViewImpl>
	{
	}
	
	public AddStakToProjViewImpl()
	{
		w = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public void updateStakes(List<Stakeholder> stakeholders)
	{
		factory.getDataProvider().setList(stakeholders);
		factory.getTabela().redraw();
	}
	
	@Override
	public List<Stakeholder> getSelecionados()
	{
		Set<Stakeholder> selectedSet = factory.getSelectionModel().getSelectedSet();
		ArrayList<Stakeholder> lista = new ArrayList<Stakeholder>();
		lista.addAll(selectedSet);
		return lista;
	}
	
	@Override
	public HasClickHandlers btAvancar()
	{
		return btAvancar;
	}
	
	@Override
	public HasClickHandlers btVoltar()
	{
		return btVoltar;
	}
	
}
