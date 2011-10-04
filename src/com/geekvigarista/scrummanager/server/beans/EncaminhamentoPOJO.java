package com.geekvigarista.scrummanager.server.beans;

import java.util.Date;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PrePersist;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.utils.IndexDirection;

@Entity("encaminhamentos")
public class EncaminhamentoPOJO
{
	
	@Id
	ObjectId id;
	
	@Reference
	@Indexed(name = "stakeholder")
	private StakeholderPOJO stakeholder; // o stakeholder que recebeu o encaminhamento;
	
	@Reference
	private EncaminhamentoPOJO encaminhamentoAnterior;
	
	//cria index no sentido decrescente
	@Indexed(name = "data", value = IndexDirection.DESC)
	private Date data;
	
	private int tempoGasto; // em horas
	private StatusRequisito status;
	
	@Transient
	private Encaminhamento encaminhamento;
	
	private String descricao;
	
	public EncaminhamentoPOJO()
	{
	}
	
	public EncaminhamentoPOJO(Encaminhamento encaminhamento)
	{
		super();
		
		if(encaminhamento == null)
		{
			this.encaminhamento = null;
			this.stakeholder = null;
			this.encaminhamentoAnterior = null;
			this.data = null;
			this.id = null;
			this.status = null;
			
		}
		else
		{
			this.encaminhamento = encaminhamento;
			
			if(encaminhamento.getStakeholder() != null)
			{
				this.stakeholder = new StakeholderPOJO(encaminhamento.getStakeholder());
			}
			
			if(encaminhamento.getEncaminhamentoAnterior() != null)
			{
				this.encaminhamentoAnterior = new EncaminhamentoPOJO(encaminhamento.getEncaminhamentoAnterior());
			}
			
			this.data = encaminhamento.getData();
			this.tempoGasto = encaminhamento.getTempoGasto();
			this.status = encaminhamento.getStatus();
			this.descricao = encaminhamento.getDescricao();
			if(encaminhamento.getId() == null)
			{
				this.id = null;
			}
			else
			{
				this.id = new ObjectId(encaminhamento.getId());
			}
		}
	}
	
	public Encaminhamento getEncaminhamento()
	{
		encaminhamento = new Encaminhamento(this.id == null ? null : this.id.toString(), stakeholder != null ? stakeholder.getStakeholder() : null,
				encaminhamentoAnterior == null ? null : encaminhamentoAnterior.getEncaminhamento(), data, tempoGasto, status, descricao);
		return encaminhamento;
	}
	
	@PrePersist
	void prePersist()
	{
		if(!status.equals(StatusRequisito.AGUARDANDO) && encaminhamentoAnterior.getId() == null)
		{
			IDaoEncaminhamento dao = new DaoEncaminhamento();
			setEncaminhamentoAnterior(new EncaminhamentoPOJO(dao.salvar(encaminhamentoAnterior.getEncaminhamento())));
		}
		if(stakeholder != null && stakeholder.getId() == null)
		{
			IDaoStakeholder daoStake = new DaoStakeholder();
			setStakeholder(new StakeholderPOJO(daoStake.salvar(stakeholder.getStakeholder())));
		}
	}
	
	public ObjectId getId()
	{
		return id;
	}
	
	public void setId(ObjectId id)
	{
		this.id = id;
	}
	
	public StakeholderPOJO getStakeholder()
	{
		return stakeholder;
	}
	
	public void setStakeholder(StakeholderPOJO stakeholder)
	{
		this.stakeholder = stakeholder;
	}
	
	public EncaminhamentoPOJO getEncaminhamentoAnterior()
	{
		return encaminhamentoAnterior;
	}
	
	public void setEncaminhamentoAnterior(EncaminhamentoPOJO encaminhamentoAnterior)
	{
		this.encaminhamentoAnterior = encaminhamentoAnterior;
	}
	
	public Date getData()
	{
		return data;
	}
	
	public void setData(Date data)
	{
		this.data = data;
	}
	
	public int getTempoGasto()
	{
		return tempoGasto;
	}
	
	public void setTempoGasto(int tempoGasto)
	{
		this.tempoGasto = tempoGasto;
	}
	
	public StatusRequisito getStatus()
	{
		return status;
	}
	
	public void setStatus(StatusRequisito status)
	{
		this.status = status;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
}
