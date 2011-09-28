package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;

public class Produto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	
	public Produto(){}

	public Produto(String descricao)
	{
		super();
		this.descricao = descricao;
	}

	public Produto(String id, String descricao)
	{
		super();
		this.id = id;
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

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	
}
