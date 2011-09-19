package com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class ExcluirStakeholderAction extends UnsecuredActionImpl<ExcluirStakeholderResult> {

	private Stakeholder stakeholder;

	public ExcluirStakeholderAction(Stakeholder stakeholder) {
		super();
		this.stakeholder = stakeholder;
	}

	@SuppressWarnings("unused")
	private ExcluirStakeholderAction() {
	}

	public Stakeholder getStakeholder() {
		return stakeholder;
	}

}
