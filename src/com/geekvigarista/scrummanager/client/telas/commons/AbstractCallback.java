package com.geekvigarista.scrummanager.client.telas.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.client.telas.componentes.loading.StatusPopupPanel;
import com.geekvigarista.scrummanager.client.telas.componentes.msgbox.MsgBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * v1: Classe abstrata com o tratamento de erros padrão para chamadas assincronas. 
 * <br></br>
 * v2: Alterada para possuir várias tentativas (em caso de queda de
 * rede ou algo do tipo), bem como o suporte a mostrar uma "status" na tela automagicamente.
 * 
 * @author caarlos0
 * 
 * @param AsynCallback<T>
 */
public abstract class AbstractCallback<T> implements AsyncCallback<T>
{
	
	/**
	 * Esse método deve conter a chamada para o serviço RCP. Usando o GWTP, fica algo como: 
	 * 
	 * <br></br>
	 * 
	 * <code>
	 * 	@Override protected void callService(AsyncCallback<DTO> asyncCallback) { 
	 * 		dispatcher.execute(new MinhaAction(), asyncCallback); 
	 * 	}
	 * </code>
	 * <br></br>
	 * 
	 * @param asyncCallback
	 */
	protected abstract void callService(AsyncCallback<T> asyncCallback);
	private StatusPopupPanel status = new StatusPopupPanel();
	
	/**
	 * Chama o método <code>go</code> usando 3 tentativas por padrão :P
	 */
	public void goDefault()
	{
		go(3);
	}
	
	/**
	 * Mostra o loading e executa a acao com o numero de tentativas passado por parametro.
	 * @param retryCount - numero de tentativas (deve ser > 0 )
	 */
	public void go(int retryCount)
	{
		assert (retryCount > 0) : "Deve possuir mais de 0 tentativas!";
		showLoadingMessage();
		execute(retryCount);
	}
	
	/**
	 * Mostra o loading.
	 */
	private void showLoadingMessage()
	{
		status.show();
	}
	
	/**
	 * Esconde o loading
	 */
	private void hideLoadingMessage()
	{
		status.hide();
	}
	
	/**
	 * Executa a acao.
	 * @param retriesLeft
	 */
	private void execute(final int retriesLeft)
	{
		callService(new AsyncCallback<T>()
		{
			public void onFailure(Throwable t)
			{
				GWT.log(t.toString(), t);
				if(retriesLeft <= 0)
				{
					hideLoadingMessage();
					AbstractCallback.this.onFailure(t);
				}
				else
				{
					execute(retriesLeft - 1);
				}
			}
			
			public void onSuccess(T result)
			{
				hideLoadingMessage();
				AbstractCallback.this.onSuccess(result);
			}
		});
	}
	
	/**
	 * tratamento padrão de erros :)
	 */
	@Override
	public void onFailure(Throwable caught)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(new Date());
		sb.append(" - ").append("Erro no servidor.");
		System.err.println(sb.toString());
		caught.printStackTrace();
		List<String> mensagens = new ArrayList<String>();
		
		if(caught.getMessage().contains("com.mongodb.MongoException$Network"))
		{
			mensagens.add("Ooops, parece que o banco de dados não quis responder sua solicitação.");
		}
		if(caught.getMessage().contains("java.lang.NullPointerException"))
		{
			mensagens.add("OH WAIT, algum de nossos macacos fez caquinha. Tente novamente mais tarde.");
		}
		else
		{
			mensagens.add(caught.getMessage());
		}
		new MsgBox(mensagens, true);
	}
}
