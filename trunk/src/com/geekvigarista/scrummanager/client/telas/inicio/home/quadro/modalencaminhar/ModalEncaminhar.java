package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.modalencaminhar;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.componentes.defaultbox.DefaultDialogBox;
import com.geekvigarista.scrummanager.client.telas.inicio.events.encaminhar.EncaminharEvent;
import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class ModalEncaminhar extends DefaultDialogBox
{
	
	private static ModalEncaminharUiBinder uiBinder = GWT.create(ModalEncaminharUiBinder.class);
	
	@UiField
	RichTextArea descricao;
	
	@UiField
	ListBox stakeholder;
	
	@UiField
	IntegerBox tempoGasto;
	
	@UiField
	Button salvar;
	
	Widget w;
	
	private final List<Stakeholder> stakeholders;
	private final EventBus eventbus;
	private final AcaoEncaminhar acao;
	private final Requisito requisito;
	
	interface ModalEncaminharUiBinder extends UiBinder<Widget, ModalEncaminhar>
	{
	}
	
	public ModalEncaminhar(final List<Stakeholder> stakeholders, final EventBus eventbus, final AcaoEncaminhar acao, final Requisito requisito)
	{
		super();
		w = uiBinder.createAndBindUi(this);
		this.add(w);
		this.stakeholders = stakeholders;
		this.eventbus = eventbus;
		this.requisito = requisito;
		this.acao = acao;
		
		inicializarCampos();
		bind();
	}
	
	private void bind()
	{
		salvar.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Stakeholder s = stakeholder.getSelectedIndex() > -1 ? stakeholders.get(stakeholder.getSelectedIndex()) : null;
				eventbus.fireEvent(new EncaminharEvent(requisito, s, descricao.getHTML(), tempoGasto.getValue(), acao));
			}
		});
	}
	
	private void inicializarCampos()
	{
		System.out.println(stakeholders.size() + "LLLLLLLLLLLLLLl");
		for(Stakeholder s : stakeholders)
		{
			if(s != null && s.getNome() != null)
			{
				stakeholder.addItem(s.getNome());
			}
		}
	}
	
}
