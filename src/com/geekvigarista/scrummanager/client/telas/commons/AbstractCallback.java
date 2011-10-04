package com.geekvigarista.scrummanager.client.telas.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe abstrata com o tratamento de erros padrão para chamadas assincronas.
 * 
 * @author caarlos0
 * 
 * @param <T>
 */
public abstract class AbstractCallback<T> implements AsyncCallback<T>
{
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
			mensagens.add("Ooops, parece que o banco de dados não quis responder sua solicitação");
		}
		else
		{
			mensagens.add(caught.getMessage());
		}
		new MsgBox(mensagens, true);
	}
}
