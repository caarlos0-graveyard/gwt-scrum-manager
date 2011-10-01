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
	
	public String dataCadastro();
	
	public String descricao();
	
	public String tempoGasto();
	
	public String data();
	
	public String selecionarProjeto();
	
	/*
	 * Home
	 */
	public String addUser();
	
	public String addStakeholder();
	
	public String addProjeto();
	
	public String addProduto();
	
	public String addRequisito();
	
	public String encaminhamentosAnteriores();
	
	public String encaminhar();
	
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
	
	/*
	 * Requisito
	 */
	public String titulo();
	
	public String prioridade();
	
	public String tempoEstimado();
	
	public String horas();
	
	public String anexos();
	
	public String semRequisitos();

	public String status();
	
	public String efetuarlogin();
	
	public String detalhes();
	
	public String produto();
	
	public String novo();
	
	public String sair();
	
	public String inicio();
	
	public String cadastroDe();
}
