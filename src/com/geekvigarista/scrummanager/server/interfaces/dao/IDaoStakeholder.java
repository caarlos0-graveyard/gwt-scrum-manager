package com.geekvigarista.scrummanager.server.interfaces.dao;

import java.util.List;

import com.geekvigarista.scrummanager.server.beans.StakeholderPOJO;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;

/**
 * Interface para o dao stakeholder, extendendo da interface IDao
 * Aqui serao descritos metodos especificos do Dao stakeholder
 * @author Raduq
 *
 */
public interface IDaoStakeholder extends IDao<Stakeholder,StakeholderPOJO> 
{
	List<Stakeholder> buscarLike(String parametro);	
}
