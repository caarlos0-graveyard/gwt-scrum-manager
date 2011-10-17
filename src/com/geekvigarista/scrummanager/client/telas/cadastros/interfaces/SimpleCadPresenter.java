package com.geekvigarista.scrummanager.client.telas.cadastros.interfaces;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * Classe abstrata básica a ser implementada pelas presenters de cadastros...
 * 
 * @author caarlos0
 * @param <V>
 */
public abstract class SimpleCadPresenter<V extends View, Proxy_ extends Proxy<?>> extends Presenter<V, Proxy_>
{
	public SalvarHandler salvarHandler = new SalvarHandler();
	public ExcluirHandler excluirHandler = new ExcluirHandler();
	public NovoHandler novoHandler = new NovoHandler();
	public CancelarHandler cancelarHandler = new CancelarHandler();
	
	public SimpleCadPresenter(EventBus eventBus, V view, Proxy_ proxy)
	{
		super(eventBus, view, proxy);
	}
	
	public abstract void doSalvar();
	
	public abstract void doNovo();
	
	public abstract void doCancelar();
	
	public abstract void doExcluir();
	
	/*
	 * inner classes dos handlers
	 */
	
	public class SalvarHandler implements KeyUpHandler, ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			doSalvar();
		}
		
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
			{
				doSalvar();
			}
		}
	}
	
	public class ExcluirHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			if(Window.confirm(Mensagem.get.temCerteza()))
			{
				doExcluir();
			}
		}
	}
	
	public class CancelarHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			doCancelar();
		}
	}
	
	public class NovoHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			doNovo();
		}
	}
}
