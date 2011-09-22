package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.LoadProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.SalvarProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.SalvarUsuarioActionHandler;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule
{
	
	/*
	 * TODO ActionHandler DI
	 * 
	 * sempre que adicionar um ActionHandler, deve-se adiciona-lo a esta classe para 
	 * que o guice possa injeta-lo atraves do servlet.
	 * 
	 */
	
	@Override
	protected void configureHandlers()
	{
		configureProjeto();
		configureStakeholder();
		configureUsuario();
	}
	
	/*
	 * adicione os binds nos métodos abaixo.
	 * 
	 * crie mais se for necessário - MANTER ORGANIZADO ESSA BAGAÇA
	 */
	
	private void configureUsuario()
	{
		bindHandler(SalvarUsuarioAction.class, SalvarUsuarioActionHandler.class);
	}
	
	private void configureProjeto()
	{
		bindHandler(SalvarProjetoAction.class, SalvarProjetoActionHandler.class);
		bindHandler(LoadProjetoAction.class, LoadProjetoActionHandler.class);
	}
	
	private void configureStakeholder()
	{
		
	}
}
