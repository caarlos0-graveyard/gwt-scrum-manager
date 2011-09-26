package com.geekvigarista.scrummanager.shared.commands.requisito.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarRequisitoResult implements Result {
	private Requisito response;
	private Projeto projeto;
	private List<String> erros;

	public SalvarRequisitoResult(Requisito response, Projeto projeto) {
		super();
		this.response = response;
		this.projeto = projeto;
	}
	
	public SalvarRequisitoResult(List<String> erros)
	{
		super();
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarRequisitoResult() {
		// serialization
	}

	public Requisito getResponse() {
		return response;
	}
	
	public List<String> getErros()
	{
		return erros;
	}

	public Projeto getProjeto()
	{
		return projeto;
	}
}
