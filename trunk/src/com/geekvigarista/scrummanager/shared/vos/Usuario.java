package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;

public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String login;
	private String senha;
	private String nome;
	
	private String confirmacaoSenha;
	private boolean administrador;
	
	public Usuario()
	{
		
	}
	
	public Usuario(String id, String login, String senha, String nome, boolean administrador)
	{
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.administrador = administrador;
	}
	
	public Usuario(String login, String senha, String nome, boolean administrador)
	{
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.administrador = administrador;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getSenha()
	{
		return senha;
	}
	
	public void setSenha(String senha)
	{
		this.senha = senha;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public boolean isAdministrador()
	{
		return administrador;
	}
	
	public void setAdministrador(boolean administrador)
	{
		this.administrador = administrador;
	}
	
	public String getConfirmacaoSenha()
	{
		return confirmacaoSenha;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha)
	{
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrador ? 1231 : 1237);
		result = prime * result + ((confirmacaoSenha == null) ? 0 : confirmacaoSenha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if(administrador != other.administrador)
			return false;
		if(confirmacaoSenha == null)
		{
			if(other.confirmacaoSenha != null)
				return false;
		}
		else if(!confirmacaoSenha.equals(other.confirmacaoSenha))
			return false;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(login == null)
		{
			if(other.login != null)
				return false;
		}
		else if(!login.equals(other.login))
			return false;
		if(nome == null)
		{
			if(other.nome != null)
				return false;
		}
		else if(!nome.equals(other.nome))
			return false;
		if(senha == null)
		{
			if(other.senha != null)
				return false;
		}
		else if(!senha.equals(other.senha))
			return false;
		return true;
	}
}
