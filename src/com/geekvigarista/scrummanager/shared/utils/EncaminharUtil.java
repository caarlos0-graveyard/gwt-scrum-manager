package com.geekvigarista.scrummanager.shared.utils;

import java.util.Date;

import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;

/**
 * Classe utilitária para encaminhamento de requisitos.
 * 
 * @author caarlos0
 * 
 */
public class EncaminharUtil
{
	
	/**
	 * Retorna um novo Encaminhamento de acordo com os dados passados por parametro.
	 * 
	 * @param encaminhamentoAnterior
	 *            - autoexplicativo
	 * @param stakeholder
	 *            - stakeholder destino
	 * @param descricao
	 *            - descricao da alteracao
	 * @param tempoGasto
	 *            - tempo gasto em horas
	 * @param acao
	 *            - {@link AcaoEncaminhar} a ser executada.
	 * @return
	 */
	private static Encaminhamento getEncaminhamento(Encaminhamento encaminhamentoAnterior, Stakeholder stakeholder, String descricao, int tempoGasto,
			AcaoEncaminhar acao)
	{
		Encaminhamento e = new Encaminhamento();
		e.setStakeholder(stakeholder);
		e.setDescricao(descricao);
		e.setTempoGasto(tempoGasto);
		e.setData(new Date());
		//		e.setEncaminhamentoAnterior(encaminhamentoAnterior); deprecated
		
		int indexstatus = encaminhamentoAnterior == null ? -1 : encaminhamentoAnterior.getStatus().ordinal();
		
		switch(acao)
		{
			case AVANCAR:
				indexstatus++;
				break;
			
			case VOLTAR:
				indexstatus--;
				break;
			
			case ALTERAR:
				break;
			
			default:
				break;
		}
		
		if(indexstatus >= 0 && indexstatus <= StatusRequisito.values().length)
		{
			e.setStatus(StatusRequisito.values()[indexstatus]);
		}
		else
		{
			e.setStatus(StatusRequisito.CONCLUIDO);
		}
		return e;
	}
	
	public static Encaminhamento getUltimoEncaminhamento(Requisito requisito)
	{
		return requisito.getEncaminhamentos().get(requisito.getEncaminhamentos().size() - 1);
	}
	
	/**
	 * Retorna o requisito pronto para ser salvo ( encaminhamento feito! )
	 * 
	 * @param requisito
	 *            - requisito a ser encaminhado
	 * @param stakeholder
	 *            - stakeholder destino
	 * @param descricao
	 * @param tempoGasto
	 * @param acao
	 * @return requisito pronto
	 */
	public static Requisito encaminhar(Requisito requisito, Stakeholder stakeholder, String descricao, int tempoGasto, AcaoEncaminhar acao)
	{
		assert (requisito != null) : "O requisito não pode ser null";
		assert (stakeholder != null) : "É obrigatório selecionar um stakeholder.";
		assert (tempoGasto > -1) : "O tempo gasto não pode ser menor que 0 ";
		Encaminhamento e = getUltimoEncaminhamento(requisito);
		requisito.getEncaminhamentos().add(getEncaminhamento(e, stakeholder, descricao, tempoGasto, acao));
		requisito.setTempoTotal(requisito.getTempoTotal() + tempoGasto);
		return requisito;
	}
}
