package com.geekvigarista.scrummanager.server.beans;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;

@Entity("stakeholders")
public class StakeholderPOJO
{
	
	@Id ObjectId id;
	private String nome;
	private PapelStakeholder papel;
	@Reference
	private ProjetoPOJO projeto;
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
		if(stakeholder.getProjeto() != null)
		{
			this.projeto = new ProjetoPOJO(stakeholder.getProjeto());
		}
	}
	
	public Stakeholder getStakeholder()
	{
		if(this.id != null)
		{
			//XXX essa porra aqui só coloquei pra testar, nao vai ter esse if
			if(projeto == null)
			{
				stakeholder = new Stakeholder(this.id.toString(), nome, papel, usuario.getUsuario());
			}else
			{
				stakeholder = new Stakeholder(this.id.toString(), nome, papel, projeto.getProjeto(), usuario.getUsuario());
			}
		}
		else
		{
			stakeholder = new Stakeholder(nome, papel, projeto.getProjeto(), usuario.getUsuario());
		}
		return stakeholder;
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
	
	public ProjetoPOJO getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(ProjetoPOJO projeto)
	{
		this.projeto = projeto;
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
