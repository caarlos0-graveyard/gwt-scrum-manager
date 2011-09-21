package com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar;

import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarEncaminhamentoAction extends UnsecuredActionImpl<SalvarEncaminhamentoResult> {

	private Encaminhamento encaminhamento;

	public SalvarEncaminhamentoAction(Encaminhamento encaminhamento) {
		super();
		this.encaminhamento = encaminhamento;
	}

	@SuppressWarnings("unused")
	private SalvarEncaminhamentoAction() {
	}

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

}
