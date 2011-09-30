package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.converters.IProjetoConverter;
import com.geekvigarista.scrummanager.client.converters.ProjetoConverter;
import com.google.gwt.inject.client.AbstractGinModule;

public class ViewBeanConverterModule extends AbstractGinModule
{

	@Override
	protected void configure()
	{
		bind(IProjetoConverter.class).to(ProjetoConverter.class);
	}
	
}
