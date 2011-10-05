package com.geekvigarista.scrummanager.client.telas.cadastros.interfaces;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
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
	
	public void doSalvar()
	{
		try
		{
			throw new Exception("você precisa dar override nessa merda");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void doNovo()
	{
		try
		{
			throw new Exception("você precisa dar override nessa merda");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void doCancelar()
	{
		try
		{
			throw new Exception("você precisa dar override nessa merda");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void doExcluir()
	{
		try
		{
			throw new Exception("você precisa dar override nessa merda");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
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
			doExcluir(); //FIXME mostrar msg confirmacao?
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
