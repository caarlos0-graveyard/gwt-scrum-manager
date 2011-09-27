package com.geekvigarista.scrummanager.shared.enums;

public enum StatusRequisito
{
	AGUARDANDO("Aguardando"), 
	EM_DESENVOLVIMENTO("Em desenvolvimento"), 
	EM_TESTE("Em testes"), 
	EM_ANALISE("Em análise"), 
	CONCLUIDO("Concluído"), 
	ATUALIZACAO("Em atualização");
	
	String desc = "";
	
	private StatusRequisito(String desc)
	{
		this.desc = desc;
	}
	
	public String desc()
	{
		return desc;
	}
	
}
