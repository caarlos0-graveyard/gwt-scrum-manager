package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.gwtplatform.dispatch.shared.Result;

public class LoadProjetoResult implements Result
{
	Projeto projeto;
	
	public LoadProjetoResult(Projeto projeto)
	{
		super();
		this.projeto = projeto;
	}
	
	@SuppressWarnings("unused")
	private LoadProjetoResult()
	{
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
}
