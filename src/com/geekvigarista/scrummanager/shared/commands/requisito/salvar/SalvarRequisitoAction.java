package com.geekvigarista.scrummanager.shared.commands.requisito.salvar;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarRequisitoAction extends UnsecuredActionImpl<SalvarRequisitoResult> {

	private Requisito requisito;

	public SalvarRequisitoAction(Requisito requisito) 
	{
		super();
		this.requisito = requisito;
	}

	@SuppressWarnings("unused")
	private SalvarRequisitoAction() 
	{
	}

	public Requisito getRequisito() 
	{
		return requisito;
	}

}
