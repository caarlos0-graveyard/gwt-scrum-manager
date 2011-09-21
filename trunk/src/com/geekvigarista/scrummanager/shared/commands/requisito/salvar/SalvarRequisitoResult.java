package com.geekvigarista.scrummanager.shared.commands.requisito.salvar;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarRequisitoResult implements Result {
	private Requisito response;

	public SalvarRequisitoResult(Requisito response) {
		super();
		this.response = response;
	}

	@SuppressWarnings("unused")
	private SalvarRequisitoResult() {
		// serialization
	}

	public Requisito getResponse() {
		return response;
	}

}
