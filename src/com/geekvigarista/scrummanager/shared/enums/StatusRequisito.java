package com.geekvigarista.scrummanager.shared.enums;

public enum StatusRequisito
{
	AGUARDANDO("Aguardando"), 
	DESENVOLVENDO("Em desenvolvimento"), 
	TESTANDO("Em testes"), 
	CONCLUIDO("Concluído");
	
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
