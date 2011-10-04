package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
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
	private int tempoTotal; // horas
	
	@Reference
	@Indexed(name = "projeto")
	private ProjetoPOJO projeto;
	
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
		this.tempoTotal = requisito.getTempoTotal();
		this.projeto = new ProjetoPOJO(requisito.getProjeto());
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
				: encaminhamentos, dataCadastro, tempoTotal, projeto.getProjeto());
		
		return requisito;
	}
	
	@PrePersist
	void prePersist()
	{	
		/*
		 * Crio o primeiro encaminhamento automagicamente.
		 */
		if(encaminhamentos == null || encaminhamentos.isEmpty())
		{
			List<EncaminhamentoPOJO> encs = new ArrayList<EncaminhamentoPOJO>();
			
			IDaoEncaminhamento dao = new DaoEncaminhamento();
			Encaminhamento e = new Encaminhamento();
			e.setData(new Date());
			e.setDescricao("Encaminhamento aguardando padrão");
			e.setEncaminhamentoAnterior(null);
			//FIXME ver que usuario padrao usar...
//			Stakeholder stk = new Stakeholder();
//			stk.setNome(requisito.getProjeto().getU)
			
			e.setStatus(StatusRequisito.AGUARDANDO);
			e.setTempoGasto(01);
			encs.add(new EncaminhamentoPOJO(dao.salvar(e)));
			setEncaminhamentos(encs);
		}
		
		if(projeto.getId() == null)
		{
			IDaoProjeto daoP = new DaoProjeto();
			setProjeto(new ProjetoPOJO(daoP.salvar(projeto.getProjeto())));
		}
	}
	
	public ProjetoPOJO getProjeto()
	{
		return projeto;
	}

	public void setProjeto(ProjetoPOJO projeto)
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
	
	public int getTempoTotal()
	{
		return tempoTotal;
	}
	
	public void setTempoTotal(int tempoTotal)
	{
		this.tempoTotal = tempoTotal;
	}
}
