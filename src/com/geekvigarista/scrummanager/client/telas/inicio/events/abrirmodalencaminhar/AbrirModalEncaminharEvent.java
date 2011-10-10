package com.geekvigarista.scrummanager.client.telas.inicio.events.abrirmodalencaminhar;

import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class AbrirModalEncaminharEvent extends GwtEvent<AbrirModalEncaminharEventHandler>
{
	
	private static final Type<AbrirModalEncaminharEventHandler> TYPE = new Type<AbrirModalEncaminharEventHandler>();
	
	public static Type<AbrirModalEncaminharEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, AcaoEncaminhar acao, Requisito requisito)
	{
		eventBus.fireEvent(new AbrirModalEncaminharEvent(acao, requisito));
	}
	
	private final AcaoEncaminhar acao;
	private final Requisito requisito;
	
	public AbrirModalEncaminharEvent(AcaoEncaminhar acao, Requisito requisito)
	{
		super();
		this.acao = acao;
		this.requisito = requisito;
	}
	
	public AcaoEncaminhar getAcao()
	{
		return acao;
	}
	
	public Requisito getRequisito()
	{
		return requisito;
	}
	
	@Override
	protected void dispatch(AbrirModalEncaminharEventHandler handler)
	{
		handler.abrirModal(this);
	}
	
	@Override
	public Type<AbrirModalEncaminharEventHandler> getAssociatedType()
	{
		return getType();
	}
}
