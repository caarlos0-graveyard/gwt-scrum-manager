package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.requisitoquadro;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.modalencaminhar.ModalEncaminhar;
import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.utils.EncaminharUtil;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequisitoQuadro extends Composite
{
	
	private static RequisitoQuadroUiBinder uiBinder = GWT.create(RequisitoQuadroUiBinder.class);
	@UiField
	Label titulo;
	
	@UiField
	Hyperlink detalhes;
	
	@UiField
	Button next;
	
	@UiField
	Button previous;
	
	private final EventBus eventbus;
	private final Usuario usuariologado;
	
	interface RequisitoQuadroUiBinder extends UiBinder<Widget, RequisitoQuadro>
	{
	}
	
	public RequisitoQuadro(final Requisito requisito, final EventBus eventbus, final Usuario usuariologado)
	{
		this.eventbus = eventbus;
		this.usuariologado = usuariologado;
		initWidget(uiBinder.createAndBindUi(this));
		titulo.setText(requisito.getTitulo());
		
		StringBuilder sb = new StringBuilder();
		sb.append(NameTokens.visreq);
		sb.append(";").append(Parameters.reqid).append("=").append(requisito.getId());
		detalhes.setTargetHistoryToken(sb.toString());
		
		switch(EncaminharUtil.getUltimoEncaminhamento(requisito).getStatus())
		{
			case AGUARDANDO:
				previous.setEnabled(false);
				break;
			
			case CONCLUIDO:
				next.setEnabled(false);
				break;
			
			default:
				break;
		}
		
		try
		{
			Encaminhamento ultimoEnc = EncaminharUtil.getUltimoEncaminhamento(requisito);
			if(ultimoEnc != null && ultimoEnc.getStakeholder() != null && ultimoEnc.getStakeholder().getUsuario() != null
					&& !ultimoEnc.getStakeholder().getUsuario().equals(usuariologado))
			{
				next.setEnabled(false);
				previous.setEnabled(false);
			}
			else
			{
				next.addClickHandler(new ClickHandler()
				{
					@Override
					public void onClick(ClickEvent event)
					{
						List<Stakeholder> s = new ArrayList<Stakeholder>();
						s.add(EncaminharUtil.getUltimoEncaminhamento(requisito).getStakeholder());
						ModalEncaminhar me = new ModalEncaminhar(s, eventbus, AcaoEncaminhar.AVANCAR, requisito);
						me.setPopupPosition(next.getAbsoluteLeft(), next.getAbsoluteTop());
						me.show();
					}
				});
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			new MsgBox("Você não pode encaminhar esse requisito.", true);
		}
		
	}
	
}
