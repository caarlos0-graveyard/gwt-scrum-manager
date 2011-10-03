package com.geekvigarista.scrummanager.shared.vos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;

public class Requisito implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String titulo;
	private String descricao;
	private PrioridadeRequisito prioridade;
	private int tempoEstimado; // horas
	private List<Encaminhamento> encaminhamentos;
	private Date dataCadastro;
	private int tempoTotal; // horas
	
	private Projeto projeto;
	
	public Requisito()
	{
		// isso evita erros variados na view, visto que um Enum nao pode ser null.
		setPrioridade(PrioridadeRequisito.MEDIA); 
	}
	
	public Requisito(String id, String titulo, String descricao, PrioridadeRequisito prioridade, int tempoEstimado, List<Encaminhamento> encaminhamentos,
			Date dataCadastro, int tempoTotal, Projeto projeto)
	{
		super();
		if(id != null)
		{
			this.id = id;
		}
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.tempoEstimado = tempoEstimado;
		if(encaminhamentos != null)
		{
			this.encaminhamentos = encaminhamentos;
		}
		this.dataCadastro = dataCadastro;
		this.tempoTotal = tempoTotal;
		this.projeto = projeto;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
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
	
	public Projeto getProjeto()
	{
		return projeto;
	}

	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public PrioridadeRequisito getPrioridade()
	{
		return prioridade;
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
	
	public List<Encaminhamento> getEncaminhamentos()
	{
		return encaminhamentos;
	}
	
	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos)
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
	
	public int getTempoTotal()
	{
		return tempoTotal;
	}
	
	public void setTempoTotal(int tempoTotal)
	{
		this.tempoTotal = tempoTotal;
	}
}
