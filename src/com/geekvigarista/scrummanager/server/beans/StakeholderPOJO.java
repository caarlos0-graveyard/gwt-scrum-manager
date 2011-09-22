package com.geekvigarista.scrummanager.server.beans;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PrePersist;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;

@Entity("stakeholders")
public class StakeholderPOJO
{
	
	@Id ObjectId id;
	private String nome;
	private PapelStakeholder papel;
	@Reference
	private UsuarioPOJO usuario;
	
	@Transient
	private Stakeholder stakeholder;
	
	/**
	 * @Deprecated usar o outro construtor.
	 */
	public StakeholderPOJO()
	{
	}
	
	/**
	 * @param usuario
	 */
	public StakeholderPOJO(Stakeholder stakeholder)
	{
		super();
		this.stakeholder = stakeholder;
		this.usuario = new UsuarioPOJO(stakeholder.getUsuario());
		if(stakeholder.getId() != null)
		{
			this.id = new ObjectId(stakeholder.getId());
		}
		this.nome = stakeholder.getNome();
		this.papel = stakeholder.getPapel();
	}
	public Stakeholder getStakeholder()
	{
		if(this.id != null)
		{
			stakeholder = new Stakeholder(id.toString(),nome, papel, usuario.getUsuario());
		}
		else
		{
			stakeholder = new Stakeholder(nome, papel, usuario.getUsuario());
		}
		return stakeholder;
	}
	
	/**
	 * Salva o usuario automaticamente toda vez antes de salvar um stakeholder.
	 * Ou seja, faz cascade.
	 */
	@PrePersist void prePersist()
	{
		IDaoUsuario dao = new DaoUsuario();
		setUsuario(new UsuarioPOJO(dao.salvar(usuario.getUsuario())));
	}
	
	public ObjectId getId()
	{
		return id;
	}
	
	public void setId(ObjectId id)
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
	
	public UsuarioPOJO getUsuario()
	{
		return usuario;
	}
	
	public void setUsuario(UsuarioPOJO usuario)
	{
		this.usuario = usuario;
	}
}
