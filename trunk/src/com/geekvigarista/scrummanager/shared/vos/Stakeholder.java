package com.geekvigarista.scrummanager.shared.vos;

import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;

public class Stakeholder
{
	
	private String id;
	private String nome;
	private PapelStakeholder papel;
	private Projeto projeto;
	private Usuario usuario;
	
	public Stakeholder(String id, String nome, PapelStakeholder papel, Projeto projeto, Usuario usuario)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.papel = papel;
		this.projeto = projeto;
		this.usuario = usuario;
	}
	
	public Stakeholder(String nome, PapelStakeholder papel, Projeto projeto, Usuario usuario)
	{
		super();
		this.nome = nome;
		this.papel = papel;
		this.projeto = projeto;
		this.usuario = usuario;
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
	
	public PapelStakeholder getPapel()
	{
		return papel;
	}
	
	public void setPapel(PapelStakeholder papel)
	{
		this.papel = papel;
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
	
	public Usuario getUsuario()
	{
		return usuario;
	}
	
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	
}
