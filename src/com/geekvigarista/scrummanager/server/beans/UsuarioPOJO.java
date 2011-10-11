package com.geekvigarista.scrummanager.server.beans;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Transient;

/**
 * Classe de Usuario que vai ser usada pelo Morphia para persistencia. Não pode ser vista no lado Client da aplicação.
 * 
 * @author Raduq
 */
@Entity("usuarios")
public class UsuarioPOJO
{
	
	@Transient
	private Usuario usuario;
	
	@Id
	ObjectId id;
	
	private String login;
	private String senha;
	private String nome;
	
	private boolean administrador;
	
	/**
	 * Construtor vazio de UsuarioPojo.
	 * 
	 * @deprecated nao instanciar, usar o outro construtor recebendo usuario.
	 */
	public UsuarioPOJO()
	{
	}
	
	/**
	 * Construtor que recebe um usuario. Necessario para converter de vo para UsuarioPOJO persistivel.
	 * 
	 * @param usuario
	 */
	public UsuarioPOJO(Usuario usuario)
	{
		super();
		this.usuario = usuario;
		if(usuario == null)
		{
			id = null;
			login = null;
			senha = null;
			nome = null;
			administrador = false;
			return;
		}
		if(usuario.getId() != null)
		{
			this.id = new ObjectId(usuario.getId());
		}
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.nome = usuario.getNome();
		this.administrador = usuario.isAdministrador();
	}
	
	/**
	 * Retorna o vo de Usuario, usavel no client. Caso o POJO tenha id, retorna um vo com id...
	 * 
	 * @return
	 */
	public Usuario getUsuario()
	{
		// se o usuarioPojo tem id entao retorna um usuario com id
		if(this.id != null)
		{
			usuario = new Usuario(this.id.toString(), this.login, this.senha, this.nome, this.administrador);
		}
		else
		{
			//se o usuarioPojo NAO tem id, retorna um sem id
			usuario = new Usuario(this.login, this.senha, this.nome, this.administrador);
		}
		return usuario;
	}
	
	public ObjectId getId()
	{
		return id;
	}
	
	public void setId(ObjectId id)
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
	
}
