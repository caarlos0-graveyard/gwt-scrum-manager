package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ColunaQuadroScrum extends Composite
{
	
	private static ColunaQuadroScrumUiBinder uiBinder = GWT.create(ColunaQuadroScrumUiBinder.class);
	
	interface ColunaQuadroScrumUiBinder extends UiBinder<Widget, ColunaQuadroScrum>
	{
	}
	
	@UiField
	Label titulo;

	@UiField
	VerticalPanel vprequisitos;
	
	public ColunaQuadroScrum(List<Requisito> requisitos, String titulo)
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.titulo.setText(titulo);
		for(Requisito requisito : requisitos)
		{
			vprequisitos.add(new RequisitoQuadro(requisito));
		}
	}
	
}
