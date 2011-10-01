package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro;

import java.util.List;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class QuadroScrumViewImpl extends ViewImpl implements QuadroScrumView
{
	
	private static QuadroScrumViewImplUiBinder uiBinder = GWT.create(QuadroScrumViewImplUiBinder.class);
	
	interface QuadroScrumViewImplUiBinder extends UiBinder<Widget, QuadroScrumViewImpl>
	{
	}
	
	Widget w;
	
	@UiField
	HorizontalPanel panelScrum;
	
	public QuadroScrumViewImpl()
	{
		w = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public void setColunas(List<ColunaQuadroScrum> colunas)
	{
		panelScrum.clear();
		if(colunas == null || colunas.isEmpty())
		{
			panelScrum.add(getLabelVazio());
			return;
		}
		
		for(ColunaQuadroScrum c : colunas)
		{
			panelScrum.add(c);
		}
	}
	
	public Label getLabelVazio()
	{
		Label l = new Label(Mensagem.get.selecionarProjeto());
		l.setStyleName("mensagemVazio");
		return l;
	}
}
