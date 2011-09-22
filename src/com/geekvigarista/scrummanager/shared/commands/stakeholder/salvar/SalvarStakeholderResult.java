package com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarStakeholderResult implements Result 
{
	private Stakeholder response;
	private List<String> erros;

	public SalvarStakeholderResult(Stakeholder response) 
	{
		super();
		this.response = response;
	}
	
	public SalvarStakeholderResult(List<String> erros)
	{
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarStakeholderResult() {
		// serialization
	}

	public Stakeholder getResponse() {
		return response;
	}
	
	public List<String> getErros()
	{
		return erros;
	}

}
