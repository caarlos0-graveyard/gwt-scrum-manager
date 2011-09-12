package com.geekvigarista.scrummanager.shared.enums;

public enum PapelStakeholder {

	DONO("Dono"), 
	GP("Gerente de Projeto"), 
	FORNECEDOR_REQUISITOS("Fornecedor de Requisitos"), 
	DESENVOLVEDOR("Desenvolvedor"), 
	TESTER("Testador"), 
	ANALISTA_SISTEMAS("Analista de Sistemas"), 
	ANALISTA_NEGOCIO("Analista de Negócios");

	String desc = "";

	private PapelStakeholder(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
