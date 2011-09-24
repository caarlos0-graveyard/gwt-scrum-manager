package com.geekvigarista.scrummanager.shared.commands.requisito.salvar;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarRequisitoAction extends UnsecuredActionImpl<SalvarRequisitoResult> {

	private Requisito requisito;
	private Projeto projeto;

	public SalvarRequisitoAction(Requisito requisito, Projeto projeto) 
	{
		super();
		this.requisito = requisito;
		this.projeto = projeto;
	}

	@SuppressWarnings("unused")
	private SalvarRequisitoAction() 
	{
	}

	public Requisito getRequisito() 
	{
		return requisito;
	}

	public Projeto getProjeto()
	{
		return projeto;
	}
}
