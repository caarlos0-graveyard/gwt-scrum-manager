package com.geekvigarista.scrummanager.client.telas.inicio.home;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ColunaQuadroScrum extends Composite
{
	
	private static ColunaQuadroScrumUiBinder uiBinder = GWT.create(ColunaQuadroScrumUiBinder.class);
	@UiField
	Label titulo;
	@UiField
	VerticalPanel vprequisitos;
	
	interface ColunaQuadroScrumUiBinder extends UiBinder<Widget, ColunaQuadroScrum>
	{
	}
	
	public ColunaQuadroScrum(List<Requisito> requisitos)
	{
		initWidget(uiBinder.createAndBindUi(this));
		for(Requisito requisito : requisitos)
		{
			vprequisitos.add(new RequisitoQuadro(requisito));
		}
	}
	
}
