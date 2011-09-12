package com.geekvigarista.scrummanager.shared.vos;

import java.util.List;

public class Usuario {

	private String id;
	private String login;
	private String senha;
	private String nome;
	private List<Projeto> projetos;

	public Usuario(String id, String login, String senha, String nome,
			List<Projeto> projetos) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.projetos = projetos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

}
