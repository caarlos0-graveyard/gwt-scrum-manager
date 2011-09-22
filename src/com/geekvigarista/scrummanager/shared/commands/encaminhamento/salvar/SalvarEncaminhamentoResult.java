package com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarEncaminhamentoResult implements Result {
	private Encaminhamento response;
	private List<String> erros;

	public SalvarEncaminhamentoResult(Encaminhamento response) {
		super();
		this.response = response;
	}
	
	public SalvarEncaminhamentoResult(List<String> erros)
	{
		super();
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarEncaminhamentoResult() {
		// serialization
	}

	public Encaminhamento getResponse() {
		return response;
	}
	
	public List<String> getErros()
	{
		return erros;
	}

}
