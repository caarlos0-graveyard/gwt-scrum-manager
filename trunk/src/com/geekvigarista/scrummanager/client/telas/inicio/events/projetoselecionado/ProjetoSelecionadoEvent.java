package com.geekvigarista.scrummanager.client.telas.inicio.events.projetoselecionado;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class ProjetoSelecionadoEvent extends GwtEvent<ProjetoSelecionadoEventHandler>
{
	
	private static final Type<ProjetoSelecionadoEventHandler> TYPE = new Type<ProjetoSelecionadoEventHandler>();
	
	public static Type<ProjetoSelecionadoEventHandler> getType()
	{
		return TYPE;
	}
	
	public static void fire(EventBus eventBus, Projeto projeto)
	{
		eventBus.fireEvent(new ProjetoSelecionadoEvent(projeto));
	}
	
	private Projeto projeto;
	
	public ProjetoSelecionadoEvent(Projeto projeto)
	{
		super();
		this.projeto = projeto;
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
	
	@Override
	protected void dispatch(ProjetoSelecionadoEventHandler handler)
	{
		handler.selecionar(this);
	}
	
	@Override
	public Type<ProjetoSelecionadoEventHandler> getAssociatedType()
	{
		return getType();
	}
}
