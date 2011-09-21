package com.geekvigarista.scrummanager.shared.commands.requisito.excluir;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class ExcluirRequisitoAction extends UnsecuredActionImpl<ExcluirRequisitoResult> {

	private Requisito requisito;

	public ExcluirRequisitoAction(Requisito requisito) {
		super();
		this.requisito = requisito;
	}

	@SuppressWarnings("unused")
	private ExcluirRequisitoAction() {
	}

	public Requisito getRequisito() {
		return requisito;
	}

}
