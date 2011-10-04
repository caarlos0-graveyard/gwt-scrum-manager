package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Projeto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private List<Stakeholder> stakeholders;
	private List<Requisito> requisitos;
	private Produto produto;
	
	public Projeto()
	{
	}
	
	public Projeto(String nome, Date dataInicio, Date dataFim, List<Stakeholder> stakeholders, List<Requisito> requisitos, Produto produto)
	{
		super();
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.stakeholders = stakeholders;
		this.requisitos = requisitos;
		this.produto = produto;
	}
	
	public Projeto(String nome, Date dataInicio, Date dataFim, List<Stakeholder> stakeholders, Produto produto)
	{
		super();
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.stakeholders = stakeholders;
		this.produto = produto;
	}
	
	public Projeto(String id, String nome, Date dataInicio, Date dataFim, List<Stakeholder> stakeholders, List<Requisito> requisitos, Produto produto)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.stakeholders = stakeholders;
		this.requisitos = requisitos;
		this.produto = produto;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public Date getDataInicio()
	{
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio)
	{
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim()
	{
		return dataFim;
	}
	
	public void setDataFim(Date dataFim)
	{
		this.dataFim = dataFim;
	}
	
	public List<Stakeholder> getStakeholders()
	{
		return stakeholders;
	}
	
	public void setStakeholders(List<Stakeholder> stakeholders)
	{
		this.stakeholders = stakeholders;
	}
	
	public List<Requisito> getRequisitos()
	{
		return requisitos;
	}
	
	public void setRequisitos(List<Requisito> requisitos)
	{
		this.requisitos = requisitos;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}
	
	
	
}
