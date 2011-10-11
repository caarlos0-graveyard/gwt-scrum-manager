package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;

import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;

public class Stakeholder implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private PapelStakeholder papel;
	private Usuario usuario;
	
	public Stakeholder()
	{
		this.papel = PapelStakeholder.DESENVOLVEDOR;
	}
	
	public Stakeholder(String id, String nome, PapelStakeholder papel, Usuario usuario)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.papel = papel;
		this.usuario = usuario;
	}
	
	public Stakeholder(String nome, PapelStakeholder papel, Usuario usuario)
	{
		super();
		this.nome = nome;
		this.papel = papel;
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
	
	public Usuario getUsuario()
	{
		return usuario;
	}
	
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((papel == null) ? 0 : papel.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Stakeholder other = (Stakeholder) obj;
		
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(nome == null)
		{
			if(other.nome != null)
				return false;
		}
		else if(!nome.equals(other.nome))
			return false;
		if(papel != other.papel)
			return false;
		if(usuario == null)
		{
			if(other.usuario != null)
				return false;
		}
		else if(!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
