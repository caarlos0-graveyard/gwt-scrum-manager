package com.geekvigarista.scrummanager.shared.commands.projeto.salvar;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarProjetoResult implements Result {
	private Projeto response;

	public SalvarProjetoResult(Projeto response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private SalvarProjetoResult() {
		// serialization
	}

	public Projeto getResponse() {
		return response;
	}

}
