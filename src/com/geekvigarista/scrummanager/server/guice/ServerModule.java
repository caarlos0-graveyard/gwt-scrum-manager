package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.SalvarUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.persistencia.dao.DaoUsuario;
import com.geekvigarista.scrummanager.shared.commands.usuario.SalvarUsuarioAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule
{
	
	@Override
	protected void configureHandlers()
	{
		
		bindHandler(SalvarUsuarioAction.class, SalvarUsuarioActionHandler.class);

//		bind(DaoUsuario.class);
	}
}
