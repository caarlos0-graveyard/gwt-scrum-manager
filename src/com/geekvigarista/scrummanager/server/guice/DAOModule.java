package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.google.inject.AbstractModule;

public class DAOModule extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(IDaoUsuario.class).to(DaoUsuario.class);
		bind(IDaoStakeholder.class).to(DaoStakeholder.class);
		bind(IDaoEncaminhamento.class).to(DaoEncaminhamento.class);
		bind(IDaoRequisito.class).to(DaoRequisito.class);
		bind(IDaoProjeto.class).to(DaoProjeto.class);
		
	}
	
}
