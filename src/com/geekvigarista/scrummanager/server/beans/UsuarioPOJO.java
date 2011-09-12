package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;

@Entity("usuarios")
public class UsuarioPOJO {

	@Transient
	private Usuario usuario;

	@Id
	private org.bson.types.ObjectId id;

	private String login;
	private String senha;
	private String nome;

	@Transient
	private String confirmacaoSenha;

	@Reference
	private List<ProjetoPOJO> projetos;

	public UsuarioPOJO() {
	}
	
	public UsuarioPOJO(Usuario usuario) {
		// TODO avoid nullpointers
		super();
		this.usuario = usuario;
		this.id = new org.bson.types.ObjectId(usuario.getId());
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.nome = usuario.getNome();
	}
	
	
	public Usuario getUsuario() {
		// TODO avoid nullpointers
		List<Projeto> projetos = new ArrayList<Projeto>();
		
		for(ProjetoPOJO p : this.projetos)
		{
			projetos.add(p.getProjeto());
		}
		
		usuario = new Usuario(this.id.toString(), this.login, this.senha, this.nome, projetos);
		return usuario;
	}


	public org.bson.types.ObjectId getId() {
		return id;
	}

	public void setId(org.bson.types.ObjectId id) {
		this.id = id;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
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

	public List<ProjetoPOJO> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoPOJO> projetos) {
		this.projetos = projetos;
	}
}
