package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.requisitoquadro;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.inicio.events.abrirmodalencaminhar.AbrirModalEncaminharEvent;
import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.utils.EncaminharUtil;
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
import com.google.gwt.user.client.ui.SimplePanel;
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
	
	@UiField
	SimplePanel simplePanel;
	
	@UiField
	Label lbResponsavel;
	
	private final EventBus eventbus;
	@SuppressWarnings(value = "unused")
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
		
		controlaDisabledBotoes(requisito);
		controlaPrioridades(requisito);
		adicionaResponsavelNaTela(requisito);
		adicionaHandlerBotoes(requisito);
	}
	
	public void adicionaResponsavelNaTela(Requisito requisito)
	{
		Stakeholder stakeholder = EncaminharUtil.getUltimoEncaminhamento(requisito).getStakeholder();
		if(stakeholder == null)
		{
			lbResponsavel.setText("Sem Stakeholder");
		}
		else
		{
			lbResponsavel.setText(stakeholder.getNome());
		}
		
	}
	
	public void adicionaHandlerBotoes(final Requisito requisito)
	{
		next.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventbus.fireEvent(new AbrirModalEncaminharEvent(AcaoEncaminhar.AVANCAR, requisito));
			}
		});
		previous.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventbus.fireEvent(new AbrirModalEncaminharEvent(AcaoEncaminhar.VOLTAR, requisito));
			}
		});
	}
	
	public void controlaDisabledBotoes(Requisito requisito)
	{
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
	}
	
	public void controlaPrioridades(Requisito requisito)
	{
		//TODO futuramente mudar para switch...
		if(requisito.getPrioridade().equals(PrioridadeRequisito.ALTA))
		{
			simplePanel.addStyleName("prioridadeAlta");
		}
		else if(requisito.getPrioridade().equals(PrioridadeRequisito.MEDIA))
		{
			simplePanel.addStyleName("prioridadeMedia");
		}
		else if(requisito.getPrioridade().equals(PrioridadeRequisito.BAIXA))
		{
			simplePanel.addStyleName("prioridadeBaixa");
		}
	}
}
