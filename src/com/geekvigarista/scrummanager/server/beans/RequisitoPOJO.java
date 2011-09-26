package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PrePersist;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;

@Entity("requisitos")
public class RequisitoPOJO
{
	
	@Id
	private ObjectId id;
	private String titulo;
	private String descricao;
	private PrioridadeRequisito prioridade;
	private int tempoEstimado; // horas
	
	@Embedded
	@Indexed(name = "encaminhamentos")
	private List<EncaminhamentoPOJO> encaminhamentos;
	private Date dataCadastro;
	private List<String> anexos;
	private int tempoTotal; // horas
	
	@Transient
	private Requisito requisito;
	
	public RequisitoPOJO()
	{
	}
	
	public RequisitoPOJO(Requisito requisito)
	{
		super();
		this.requisito = requisito;
		if(requisito.getId() != null)
		{
			this.id = new ObjectId(requisito.getId());
		}
		this.titulo = requisito.getTitulo();
		this.descricao = requisito.getDescricao();
		this.prioridade = requisito.getPrioridade();
		this.tempoEstimado = requisito.getTempoEstimado();
		
		if(requisito.getEncaminhamentos() != null)
		{
			this.encaminhamentos = new ArrayList<EncaminhamentoPOJO>();
			for(Encaminhamento e : requisito.getEncaminhamentos())
			{
				this.encaminhamentos.add(new EncaminhamentoPOJO(e));
			}
		}
		
		this.dataCadastro = requisito.getDataCadastro();
		this.anexos = requisito.getAnexos();
		this.tempoTotal = requisito.getTempoTotal();
	}
	
	public Requisito getRequisito()
	{
		List<Encaminhamento> encaminhamentos = new ArrayList<Encaminhamento>();
		if(this.encaminhamentos != null)
		{
			for(EncaminhamentoPOJO e : this.encaminhamentos)
			{
				encaminhamentos.add(e.getEncaminhamento());
			}
		}
		requisito = new Requisito((this.id == null) ? null : this.id.toString(), titulo,descricao, prioridade, tempoEstimado, (encaminhamentos == null) ? null
				: encaminhamentos, dataCadastro, anexos, tempoTotal);
		
		return requisito;
	}
	
	@PrePersist
	void prePersist()
	{	
		/*
		 * Requisito contem só campos embed, entao
		 * nao precisa de pre-persist.
		 */
	}
	
	public ObjectId getId()
	{
		return id;
	}
	
	public void setId(ObjectId id)
	{
		this.id = id;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	
	public PrioridadeRequisito getPrioridade()
	{
		return prioridade;
	}
	
	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public void setPrioridade(PrioridadeRequisito prioridade)
	{
		this.prioridade = prioridade;
	}
	
	public int getTempoEstimado()
	{
		return tempoEstimado;
	}
	
	public void setTempoEstimado(int tempoEstimado)
	{
		this.tempoEstimado = tempoEstimado;
	}
	
	public List<EncaminhamentoPOJO> getEncaminhamentos()
	{
		return encaminhamentos;
	}
	
	public void setEncaminhamentos(List<EncaminhamentoPOJO> encaminhamentos)
	{
		this.encaminhamentos = encaminhamentos;
	}
	
	public Date getDataCadastro()
	{
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro)
	{
		this.dataCadastro = dataCadastro;
	}
	
	public List<String> getAnexos()
	{
		return anexos;
	}
	
	public void setAnexos(List<String> anexos)
	{
		this.anexos = anexos;
	}
	
	public int getTempoTotal()
	{
		return tempoTotal;
	}
	
	public void setTempoTotal(int tempoTotal)
	{
		this.tempoTotal = tempoTotal;
	}
}
