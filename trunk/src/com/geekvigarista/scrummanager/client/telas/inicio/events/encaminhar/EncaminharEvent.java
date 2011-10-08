package com.geekvigarista.scrummanager.client.telas.inicio.events.encaminhar;

import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class EncaminharEvent extends GwtEvent<EncaminharEventHandler>
{
	
	private static final Type<EncaminharEventHandler> TYPE = new Type<EncaminharEventHandler>();
	
	public static Type<EncaminharEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, Requisito requisito, Stakeholder stakeholder, String descricao, int tempoGasto, AcaoEncaminhar acao)
	{
		eventBus.fireEvent(new EncaminharEvent(requisito, stakeholder, descricao, tempoGasto, acao));
	}
	
	private Requisito requisito;
	private Stakeholder stakeholder;
	private String descricao;
	private int tempoGasto;
	private AcaoEncaminhar acao;
	
	public EncaminharEvent(Requisito requisito, Stakeholder stakeholder, String descricao, int tempoGasto, AcaoEncaminhar acao)
	{
		super();
		this.requisito = requisito;
		this.stakeholder = stakeholder;
		this.descricao = descricao;
		this.tempoGasto = tempoGasto;
		this.acao = acao;
	}
	
	public Requisito getRequisito()
	{
		return requisito;
	}
	
	public void setRequisito(Requisito requisito)
	{
		this.requisito = requisito;
	}
	
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
	
	public void setStakeholder(Stakeholder stakeholder)
	{
		this.stakeholder = stakeholder;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	public int getTempoGasto()
	{
		return tempoGasto;
	}
	
	public void setTempoGasto(int tempoGasto)
	{
		this.tempoGasto = tempoGasto;
	}
	
	public AcaoEncaminhar getAcao()
	{
		return acao;
	}
	
	public void setAcao(AcaoEncaminhar acao)
	{
		this.acao = acao;
	}
	
	@Override
	protected void dispatch(EncaminharEventHandler handler)
	{
		handler.encaminhar(this);
	}
	
	@Override
	public Type<EncaminharEventHandler> getAssociatedType()
	{
		return getType();
	}
}
