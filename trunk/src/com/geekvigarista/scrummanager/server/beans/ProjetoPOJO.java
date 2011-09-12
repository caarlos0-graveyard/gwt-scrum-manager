package com.geekvigarista.scrummanager.server.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Transient;

@Entity("projetos")
public class ProjetoPOJO {

	@Transient
	private Projeto projeto;
	
	private String id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private List<StakeholderPOJO> stakeholders;
	private List<RequisitoPOJO> requisitos;
	
	public ProjetoPOJO() {
	}
	
	/**
	 * @param projeto
	 */
	public ProjetoPOJO(Projeto projeto) {
		// TODO avoid nullpointers
		super();
		this.projeto = projeto;
		this.dataFim = projeto.getDataFim();
		this.dataInicio = projeto.getDataInicio();
		this.nome = projeto.getNome();
		
		this.requisitos = new ArrayList<RequisitoPOJO>();
		for(Requisito r : projeto.getRequisitos())
		{
			this.requisitos.add(new RequisitoPOJO(r));
		}
		
		this.stakeholders = new ArrayList<StakeholderPOJO>();
		for(Stakeholder s : projeto.getStakeholders())
		{
			this.stakeholders.add(new StakeholderPOJO(s));
		}
		
	}
	
	
	public Projeto getProjeto() {
		// TODO avoid nullpointers
		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		for(StakeholderPOJO s : this.stakeholders)
		{
			stakeholders.add(s.getStakeholder());
		}
		
		List<Requisito> requisitos = new ArrayList<Requisito>();
		for(RequisitoPOJO r : this.requisitos)
		{
			requisitos.add(r.getRequisito());
		}
		
		this.projeto = new Projeto(this.id.toString(), nome, dataInicio, dataFim, stakeholders, requisitos);
		return this.projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public List<StakeholderPOJO> getStakeholders() {
		return stakeholders;
	}
	public void setStakeholders(List<StakeholderPOJO> stakeholders) {
		this.stakeholders = stakeholders;
	}
	public List<RequisitoPOJO> getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(List<RequisitoPOJO> requisitos) {
		this.requisitos = requisitos;
	}
}
