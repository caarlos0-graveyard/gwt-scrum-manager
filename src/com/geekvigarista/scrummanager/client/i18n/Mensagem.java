package com.geekvigarista.scrummanager.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface Mensagem extends Constants
{
	public static class MENSAGEM
	{
		private static Mensagem instance = null;
		
		private static final Mensagem getInstance()
		{
			if(instance == null)
				instance = GWT.create(Mensagem.class);
			return instance;
		}
	}
	
	public static Mensagem get = MENSAGEM.getInstance();
	
	/*
	 * add USer
	 */
	public String nome();
	
	public String login();
	
	public String senha();
	
	public String confirmacaSenha();
	
	/*
	 * Generics
	 */
	public String salvar();
	
	public String cancelar();
}
