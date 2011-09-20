package com.geekvigarista.scrummanager.shared.commands.projeto.salvar;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarProjetoAction extends UnsecuredActionImpl<SalvarProjetoResult> {

	private Projeto projeto;

	public SalvarProjetoAction(Projeto projeto) {
		super();
		this.projeto = projeto;
	}

	@SuppressWarnings("unused")
	private SalvarProjetoAction() {
	}

	public Projeto getProjeto() {
		return projeto;
	}

}
