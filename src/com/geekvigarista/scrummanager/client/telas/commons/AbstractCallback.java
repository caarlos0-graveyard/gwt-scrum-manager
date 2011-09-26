package com.geekvigarista.scrummanager.client.telas.commons;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe abstrata com o tratamento de erros padrão para chamadas assincronas.
 * @author caarlos0
 *
 * @param <T>
 */
public abstract class AbstractCallback<T> implements AsyncCallback<T>
{
	@Override
	public void onFailure(Throwable caught)
	{
		// TODO mostrar uma mensagem de um jeito mais bonito, não window.alert.
		StringBuilder sb = new StringBuilder();
		sb.append(new Date());
		sb.append(" - ").append("Erro na comunicação com o servidor.");
		System.err.println(sb.toString());
		caught.printStackTrace();
		Window.alert("Ocorreu um erro na comunicação com o servidor");
	}
	
}
