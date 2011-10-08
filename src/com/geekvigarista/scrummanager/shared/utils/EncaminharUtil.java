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
	public static Encaminhamento encaminhar(Encaminhamento encaminhamentoAnterior, Stakeholder stakeholder, String descricao, int tempoGasto,
			AcaoEncaminhar acao)
	{
		Encaminhamento e = new Encaminhamento();
		e.setStakeholder(stakeholder);
		e.setDescricao(descricao);
		e.setTempoGasto(tempoGasto);
		e.setData(new Date());
		e.setEncaminhamentoAnterior(encaminhamentoAnterior);
		
		int indexstatus = encaminhamentoAnterior == null ? -1 : encaminhamentoAnterior.getStatus().ordinal();
		
		switch(acao)
		{
			case AVANCAR:
				indexstatus++;
				break;
			
			case ALTERAR:
				indexstatus--;
				
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
}
