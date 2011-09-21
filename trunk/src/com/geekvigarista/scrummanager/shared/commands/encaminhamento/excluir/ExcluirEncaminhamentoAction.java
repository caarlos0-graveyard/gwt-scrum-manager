package com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir;

import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class ExcluirEncaminhamentoAction extends UnsecuredActionImpl<ExcluirEncaminhamentoResult> {

	private Encaminhamento encaminhamento;

	public ExcluirEncaminhamentoAction(Encaminhamento encaminhamento) {
		super();
		this.encaminhamento = encaminhamento;
	}

	@SuppressWarnings("unused")
	private ExcluirEncaminhamentoAction() {
	}

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

}
