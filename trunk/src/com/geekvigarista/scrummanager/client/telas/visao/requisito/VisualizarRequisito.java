package com.geekvigarista.scrummanager.client.telas.visao.requisito;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class VisualizarRequisito extends Presenter<VisualizarRequisito.VisReqView, VisualizarRequisito.VisReqProxy>
{
	/*
	 * inner interfaces classe
	 */
	
	@ProxyCodeSplit
	@NameToken(NameTokens.visreq)
	public interface VisReqProxy extends ProxyPlace<VisualizarRequisito>
	{
	}
	
	public interface VisReqView extends View
	{
		
	}
	
	/*
	 * Atributos
	 */
	
	
	
	/*
	 * Construtores
	 */
	
	@Inject
	public VisualizarRequisito(EventBus eventBus, VisReqView view, VisReqProxy proxy)
	{
		super(eventBus, view, proxy);
	}
	
	
	
	/*
	 * METHODS
	 */
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
}
