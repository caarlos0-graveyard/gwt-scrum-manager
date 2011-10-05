package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PostLoad;
import com.google.code.morphia.annotations.PrePersist;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.utils.IndexDirection;

@Entity("projetos")
public class ProjetoPOJO
{
	@Transient
	private Projeto projeto;
	
	@Id
	ObjectId id;
	
	@Indexed(name = "nome", unique = true, dropDups = true, value = IndexDirection.ASC)
	private String nome;
	
	private Date dataInicio;
	private Date dataFim;
	
	@Indexed(name = "stakeholders")
	@Reference
	private List<StakeholderPOJO> stakeholders;
	
	@Indexed(name = "produto")
	private ProdutoPOJO produto;
	
	@Transient
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
		if(projeto == null)
			projeto = new Projeto();
		this.projeto = projeto;
		this.dataFim = projeto.getDataFim();
		this.dataInicio = projeto.getDataInicio();
		this.nome = projeto.getNome();
		this.produto = new ProdutoPOJO(projeto.getProduto());
		if(projeto.getRequisitos() != null)
		{
			this.requisitos = new ArrayList<RequisitoPOJO>();
			for(Requisito r : projeto.getRequisitos())
			{
				RequisitoPOJO rPojo = new RequisitoPOJO(r);
				this.requisitos.add(rPojo);
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
	
//	@PrePersist
//	void prePersist()
//	{
//		if(produto != null && produto.getId() == null)
//		{
////			IDaoProduto daoP = new DaoProduto();
//			setProduto(new ProdutoPOJO(produto.getProduto()));
//		}
//		//		if(requisitos != null && !requisitos.isEmpty())
//		//		{
//		//			IDaoRequisito daoR = new DaoRequisito();
//		//			List<RequisitoPOJO> requisitos = new ArrayList<RequisitoPOJO>();
//		//			for(RequisitoPOJO r : this.requisitos)
//		//			{
//		//				if(r.getId() == null)
//		//				{
//		//					Requisito rSalvo = daoR.salvar(r.getRequisito());
//		//					requisitos.add(new RequisitoPOJO(rSalvo));
//		//				}
//		//				else
//		//				{
//		//					requisitos.add(r);
//		//				}
//		//			}
//		//			if(!requisitos.isEmpty())
//		//			{
//		//				this.requisitos.clear();
//		//				this.requisitos = requisitos;
//		//			}
//		//			
//		//		}
//	}
	
	@PostLoad
	void postLoad()
	{
		try
		{
			IDaoRequisito daoR = new DaoRequisito();
			List<Requisito> requisitos = daoR.buscarByProjeto(projeto);
			if(requisitos != null)
			{
				for(Requisito r : requisitos)
				{
					projeto.getRequisitos().add(r);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		//		if(this.requisitos != null)
		//		{
		//			for(RequisitoPOJO r : this.requisitos)
		//			{
		//				Requisito req = r.getRequisito();
		//				requisitos.add(req);
		//			}
		//		}
		
		if(this.id == null)
		{
			//XXX só para teste, este if nao vai ter...
			if(this.requisitos == null)
			{
				this.projeto = new Projeto(nome, dataInicio, dataFim, stakeholders, produto.getProduto());
			}
			else
			{
				this.projeto = new Projeto(nome, dataInicio, dataFim, stakeholders, requisitos, produto.getProduto());
			}
		}
		else
		{
			this.projeto = new Projeto(this.id.toString(), nome, dataInicio, dataFim, stakeholders, requisitos, produto.getProduto());
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

	public ProdutoPOJO getProduto()
	{
		return produto;
	}

	public void setProduto(ProdutoPOJO produto)
	{
		this.produto = produto;
	}
}
