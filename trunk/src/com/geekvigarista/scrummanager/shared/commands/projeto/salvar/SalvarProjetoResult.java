package com.geekvigarista.scrummanager.shared.commands.projeto.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarProjetoResult implements Result {
	private Projeto response;
	private List<String> erros;

	public SalvarProjetoResult(Projeto response) {
		super();
		this.response = response;
	}
	
	public SalvarProjetoResult(Projeto response, List<String> erros) {
		super();
		this.response = response;
		this.erros = erros;
	}
	
	public SalvarProjetoResult(List<String> erros)
	{
		super();
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarProjetoResult() {
		// serialization
	}

	public Projeto getResponse() {
		return response;
	}
	public List<String> getErros()
	{
		return erros;
	}

}
