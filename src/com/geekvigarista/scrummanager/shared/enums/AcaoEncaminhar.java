package com.geekvigarista.scrummanager.shared.enums;

/**
 * ENUM que define o fluxo do encaminhamento do requisito
 * @author caarlos0
 *
 */
public enum AcaoEncaminhar
{
	/**
	 * volta um status encaminhamento 
	 */
	VOLTAR, 
	/**
	 * mantem o status, podendo encaminhar para outro stakeholder, ou adicionar um comentário somente..
	 */
	ALTERAR, 
	/**
	 * avança um status encaminhamento
	 */
	AVANCAR;
}
