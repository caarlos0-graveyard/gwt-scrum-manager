package com.geekvigarista.scrummanager.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

/**
 * Classe de i18n para mensagens que necessitam parametros, porque o uiBinder não aceita usar interfaces que contenham métodos com parametros.
 * 
 * @author caarlos0
 * 
 */
public interface MensagemParams extends Constants
{
	public static class MENSAGEM_PARAM
	{
		private static MensagemParams instance = null;
		
		private static final MensagemParams getInstance()
		{
			if(instance == null)
				instance = GWT.create(MensagemParams.class);
			return instance;
		}
	}
	
	public static MensagemParams get = MENSAGEM_PARAM.getInstance();
	
	
	public String tempoHoras(int horas);
	
}
