package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.utils.IndexDirection;

@Entity("projetos")
public class ProjetoPOJO
{
	
	@Transient
	private Projeto projeto;
	
	@Id
	ObjectId id;
	
	@Indexed(name="nome",unique=true,dropDups=true,value=IndexDirection.ASC)
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	@Indexed(name="stakeholders")
	@Embedded
	private List<StakeholderPOJO> stakeholders;
	@Indexed(name="requisitos")
	@Embedded
	private List<RequisitoPOJO> requisitos;
	
	public ProjetoPOJO()
	{
	}
	
	/**
	 * @param projeto
	 */
	public ProjetoPOJO(Projeto projeto)
	{
		// TODO avoid nullpointers
		super();
		this.projeto = projeto;
		this.dataFim = projeto.getDataFim();
		this.dataInicio = projeto.getDataInicio();
		this.nome = projeto.getNome();
		
		if(projeto.getRequisitos() != null)
		{
			this.requisitos = new ArrayList<RequisitoPOJO>();
			for(Requisito r : projeto.getRequisitos())
			{
				this.requisitos.add(new RequisitoPOJO(r));
			}
		}
		
		if(projeto.getStakeholders() != null)
		{
			this.stakeholders = new ArrayList<StakeholderPOJO>();
			
			for(Stakeholder s : projeto.getStakeholders())
			{
				this.stakeholders.add(new StakeholderPOJO(s));
			}
		}
		if(projeto.getId() != null && !projeto.getId().isEmpty())
		{
			this.id = new ObjectId(projeto.getId());
		}
		
	}
	
	public Projeto getProjeto()
	{
		// TODO avoid nullpointers
		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		if(this.stakeholders != null)
		{
			for(StakeholderPOJO s : this.stakeholders)
			{
				stakeholders.add(s.getStakeholder());
			}
		}
		
		List<Requisito> requisitos = new ArrayList<Requisito>();
		if(this.requisitos != null)
		{
			for(RequisitoPOJO r : this.requisitos)
			{
				requisitos.add(r.getRequisito());
			}
		}
		
		if(this.id == null)
		{
			//XXX só para teste, este if nao vai ter...
			if(this.requisitos == null)
			{
				this.projeto = new Projeto(nome, dataInicio, dataFim, stakeholders);
			}
			else
			{
				this.projeto = new Projeto(nome, dataInicio, dataFim, stakeholders, requisitos);
			}
		}
		else
		{
			this.projeto = new Projeto(this.id.toString(), nome, dataInicio, dataFim, stakeholders, requisitos);
		}
		return this.projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
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
	
	public List<StakeholderPOJO> getStakeholders()
	{
		return stakeholders;
	}
	
	public void setStakeholders(List<StakeholderPOJO> stakeholders)
	{
		this.stakeholders = stakeholders;
	}
	
	public List<RequisitoPOJO> getRequisitos()
	{
		return requisitos;
	}
	
	public void setRequisitos(List<RequisitoPOJO> requisitos)
	{
		this.requisitos = requisitos;
	}
}
