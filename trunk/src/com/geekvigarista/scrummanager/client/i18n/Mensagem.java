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
	 * Generics
	 */
	public String salvar();
	
	public String cancelar();
	
	public String nome();
	
	public String mais();
	
	public String menos();
	
	/*
	 * Home
	 */
	public String addUser();
	
	public String addStakeholder();
	
	public String addProjeto();
	
	public String addProduto();
	
	public String addRequisito();
	
	/*
	 * add USer
	 */
	
	public String login();
	
	public String senha();
	
	public String confirmacaSenha();
	
	/*
	 * add stakeholder
	 */
	public String papelStakeholder();
	
	public String usuario();
	
	/*
	 * Projeto
	 */
	public String projeto();
	
	public String dataInicio();
	
	public String dataFim();
	
	public String addRequisitos();
	
	public String addStakeholders();
	
	public String requisitos();
	
	public String stakeholders();
	
	public String necessarioSalvarProjeto();
	
}
