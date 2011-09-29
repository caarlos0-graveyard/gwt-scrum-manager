package com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class SalvarStakeholderAction extends UnsecuredActionImpl<SalvarStakeholderResult>
{
	
	private Stakeholder stakeholder;
	
	public SalvarStakeholderAction(Stakeholder stakeholder)
	{
		super();
		this.stakeholder = stakeholder;
	}
	
	@SuppressWarnings("unused")
	private SalvarStakeholderAction()
	{
	}
	
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
}
