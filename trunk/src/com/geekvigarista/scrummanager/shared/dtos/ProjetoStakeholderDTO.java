package com.geekvigarista.scrummanager.shared.dtos;

import java.io.Serializable;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;

public class ProjetoStakeholderDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Projeto projeto;
	private Stakeholder stakeholder;
	
	public ProjetoStakeholderDTO(){}
	public ProjetoStakeholderDTO(Projeto projeto, Stakeholder stakeholder)
	{
		super();
		this.projeto = projeto;
		this.stakeholder = stakeholder;
	}
	
	public ProjetoStakeholderDTO(Projeto projeto)
	{
		super();
		this.projeto = projeto;
	}
	
	/**
	 * @return the projeto
	 */
	public Projeto getProjeto()
	{
		return projeto;
	}
	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
	/**
	 * @return the stakeholder
	 */
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
	/**
	 * @param stakeholder the stakeholder to set
	 */
	public void setStakeholder(Stakeholder stakeholder)
	{
		this.stakeholder = stakeholder;
	}
	
	
}
