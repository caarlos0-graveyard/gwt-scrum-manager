package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import java.util.List;

import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarProjetoListResult implements Result
{
	List<ProjetoStakeholderDTO> projetos;
	
	public BuscarProjetoListResult(List<ProjetoStakeholderDTO> projetos)
	{
		super();
		this.projetos = projetos;
	}
	
	@SuppressWarnings("unused")
	private BuscarProjetoListResult()
	{
	}
	
	public List<ProjetoStakeholderDTO> getProjetos()
	{
		return projetos;
	}
	
}
