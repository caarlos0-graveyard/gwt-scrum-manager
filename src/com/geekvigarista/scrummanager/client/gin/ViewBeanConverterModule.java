package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.converters.ProjetoConverter;
import com.geekvigarista.scrummanager.client.converters.RequisitoConverter;
import com.geekvigarista.scrummanager.client.converters.StakeholderConverter;
import com.geekvigarista.scrummanager.client.converters.UsuarioConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IProjetoConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IRequisitoConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IStakeholderConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IUsuarioConverter;
import com.google.gwt.inject.client.AbstractGinModule;

public class ViewBeanConverterModule extends AbstractGinModule
{
	
	@Override
	protected void configure()
	{
		bind(IProjetoConverter.class).to(ProjetoConverter.class);
		
		bind(IStakeholderConverter.class).to(StakeholderConverter.class);
		
		bind(IUsuarioConverter.class).to(UsuarioConverter.class);
		
		bind(IRequisitoConverter.class).to(RequisitoConverter.class);
	}
	
}
