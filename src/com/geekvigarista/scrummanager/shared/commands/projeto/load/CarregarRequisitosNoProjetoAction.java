package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class CarregarRequisitosNoProjetoAction extends UnsecuredActionImpl<LoadProjetoResult>
{
	private Projeto projeto;
	
	public CarregarRequisitosNoProjetoAction(Projeto projeto)
	{
		super();
		this.projeto = projeto;
	}
	
	@SuppressWarnings("unused")
	private CarregarRequisitosNoProjetoAction()
	{
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
}
