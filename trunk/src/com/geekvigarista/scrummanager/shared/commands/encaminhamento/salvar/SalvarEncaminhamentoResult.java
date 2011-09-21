package com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar;

import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarEncaminhamentoResult implements Result {
	private Encaminhamento response;

	public SalvarEncaminhamentoResult(Encaminhamento response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private SalvarEncaminhamentoResult() {
		// serialization
	}

	public Encaminhamento getResponse() {
		return response;
	}

}
