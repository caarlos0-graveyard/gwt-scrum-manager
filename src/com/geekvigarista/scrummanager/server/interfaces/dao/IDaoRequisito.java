package com.geekvigarista.scrummanager.server.interfaces.dao;

import java.util.List;

import com.geekvigarista.scrummanager.server.beans.RequisitoPOJO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;

/**
 * Interface para o dao requisito, extendendo da interface IDao Aqui serao descritos metodos especificos do Dao Requisito
 * 
 * @author Raduq
 * 
 */
public interface IDaoRequisito extends IDao<Requisito, RequisitoPOJO>
{
	List<Requisito> buscarByProjeto(Projeto projeto);
}
