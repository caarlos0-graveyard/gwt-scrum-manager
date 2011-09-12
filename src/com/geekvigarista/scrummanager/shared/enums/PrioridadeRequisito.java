package com.geekvigarista.scrummanager.shared.enums;

public enum PrioridadeRequisito {
	
	ALTA("Alta"), BAIXA("Baixa"), MEDIA("Média");
	
	String desc = "";

	private PrioridadeRequisito(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
