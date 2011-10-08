package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.coluna;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.requisitoquadro.RequisitoQuadro;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
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
	
	private final EventBus eventbus;
	private final Usuario usuariologado;
	
	public ColunaQuadroScrum(List<Requisito> requisitos, String titulo, final EventBus eventbus, final Usuario usuariologado)
	{
		this.eventbus = eventbus;
		this.usuariologado = usuariologado;
		initWidget(uiBinder.createAndBindUi(this));
		this.titulo.setText(titulo);
		for(Requisito requisito : requisitos)
		{
			vprequisitos.add(new RequisitoQuadro(requisito, eventbus, usuariologado));
		}
	}
	
}
