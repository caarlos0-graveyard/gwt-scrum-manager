package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.google.inject.AbstractModule;

public class DAOModule extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(IDaoUsuario.class).to(DaoUsuario.class);
	}
	
}
