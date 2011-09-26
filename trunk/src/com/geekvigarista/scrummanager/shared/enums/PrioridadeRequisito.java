package com.geekvigarista.scrummanager.shared.enums;

public enum PrioridadeRequisito {
	
	ALTA("Alta"), MEDIA("Média"), BAIXA("Baixa");
	
	String desc = "";

	private PrioridadeRequisito(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
