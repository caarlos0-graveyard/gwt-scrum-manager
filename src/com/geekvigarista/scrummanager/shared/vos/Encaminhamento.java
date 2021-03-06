package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;
import java.util.Date;

import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;

public class Encaminhamento implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Stakeholder stakeholder; // o stakeholder que recebeu o
										// encaminhamento;
	private Encaminhamento encaminhamentoAnterior;
	private Date data;
	private int tempoGasto; // em horas
	private StatusRequisito status;
	
	private String descricao;
	
	public Encaminhamento()
	{
	}
	
	public Encaminhamento(String id, Stakeholder stakeholder, Encaminhamento encaminhamentoAnterior, Date data, int tempoGasto,
			StatusRequisito status,String descricao)
	{
		super();
		if(id != null)
		{
			this.id = id;
		}
		if(stakeholder != null)
		{
			this.stakeholder = stakeholder;
		}
		if(encaminhamentoAnterior != null)
		{
			this.encaminhamentoAnterior = encaminhamentoAnterior;
		}
		this.data = data;
		this.tempoGasto = tempoGasto;
		this.status = status;
		this.descricao = descricao;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
	
	public void setStakeholder(Stakeholder stakeholder)
	{
		this.stakeholder = stakeholder;
	}
	
	public Encaminhamento getEncaminhamentoAnterior()
	{
		return encaminhamentoAnterior;
	}
	
	public void setEncaminhamentoAnterior(Encaminhamento encaminhamentoAnterior)
	{
		this.encaminhamentoAnterior = encaminhamentoAnterior;
	}
	
	public Date getData()
	{
		return data;
	}
	
	public void setData(Date data)
	{
		this.data = data;
	}
	
	public int getTempoGasto()
	{
		return tempoGasto;
	}
	
	public void setTempoGasto(int tempoGasto)
	{
		this.tempoGasto = tempoGasto;
	}
	
	public StatusRequisito getStatus()
	{
		return status;
	}
	
	public void setStatus(StatusRequisito status)
	{
		this.status = status;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	
}
