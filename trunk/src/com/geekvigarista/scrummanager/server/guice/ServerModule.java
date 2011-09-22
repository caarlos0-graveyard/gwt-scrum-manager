package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento.ExcluirEncaminhamentoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento.SalvarEncaminhamentoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.LoadProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.SalvarProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito.ExcluirRequisitoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito.SalvarRequisitoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.BuscarStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.ExcluirStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.SalvarStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.SalvarUsuarioActionHandler;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir.ExcluirEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar.SalvarEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir.ExcluirStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderAction;
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
		configureEncaminhamento();
		configureRequisito();
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
		bindHandler(SalvarStakeholderAction.class, SalvarStakeholderActionHandler.class);
		bindHandler(ExcluirStakeholderAction.class, ExcluirStakeholderActionHandler.class);
		bindHandler(BuscarStakeholderAction.class, BuscarStakeholderActionHandler.class);
	}
	
	private void configureEncaminhamento()
	{
		bindHandler(SalvarEncaminhamentoAction.class, SalvarEncaminhamentoActionHandler.class);
		bindHandler(ExcluirEncaminhamentoAction.class, ExcluirEncaminhamentoActionHandler.class);
	}
	
	private void configureRequisito()
	{
		bindHandler(SalvarRequisitoAction.class, SalvarRequisitoActionHandler.class);
		bindHandler(ExcluirRequisitoAction.class, ExcluirRequisitoActionHandler.class);
	}
	
}
