package com.geekvigarista.scrummanager.server.guice;

import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento.ExcluirEncaminhamentoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.encaminhamento.SalvarEncaminhamentoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto.BuscarTodosProdutosActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto.LoadProdutoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.produto.SalvarProdutoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.BuscarProjetosByUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.CarregarRequisitosNoProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.LoadProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.projeto.SalvarProjetoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito.ExcluirRequisitoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito.LoadRequisitoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.requisito.SalvarRequisitoActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.BuscarStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.ExcluirStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.LoadStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.stakeholder.SalvarStakeholderActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.BuscarUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.ExcluirUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.LoadUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.SalvarUsuarioActionHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login.LoginHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login.LogoutHandler;
import com.geekvigarista.scrummanager.server.actionhandlers.cadastros.usuario.login.VerificaUsuarioLogadoHandler;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.excluir.ExcluirEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.encaminhamento.salvar.SalvarEncaminhamentoAction;
import com.geekvigarista.scrummanager.shared.commands.produto.busca.BuscaTodosProdutosAction;
import com.geekvigarista.scrummanager.shared.commands.produto.load.LoadProdutoAction;
import com.geekvigarista.scrummanager.shared.commands.produto.salvar.SalvarProdutoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetosByUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.CarregarRequisitosNoProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.buscar.BuscarRequisitoByIdAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.excluir.ExcluirRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderByIdAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.excluir.ExcluirStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioByIdAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.excluir.ExcluirUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LoginUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LogoutUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.VerificaUsuarioLogadoAction;
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
		configureProduto();
	}
	
	/*
	 * adicione os binds nos métodos abaixo.
	 * 
	 * crie mais se for necessário - MANTER ORGANIZADO ESSA BAGAÇA
	 */
	
	private void configureUsuario()
	{
		bindHandler(SalvarUsuarioAction.class, SalvarUsuarioActionHandler.class);
		bindHandler(BuscarUsuarioByIdAction.class, LoadUsuarioActionHandler.class);
		bindHandler(ExcluirUsuarioAction.class, ExcluirUsuarioActionHandler.class);
		bindHandler(BuscarUsuarioAction.class, BuscarUsuarioActionHandler.class);
		bindHandler(LoginUsuarioAction.class, LoginHandler.class);
		bindHandler(VerificaUsuarioLogadoAction.class, VerificaUsuarioLogadoHandler.class);
		bindHandler(LogoutUsuarioAction.class, LogoutHandler.class);
	}
	
	private void configureProjeto()
	{
		bindHandler(SalvarProjetoAction.class, SalvarProjetoActionHandler.class);
		bindHandler(LoadProjetoAction.class, LoadProjetoActionHandler.class);
		bindHandler(BuscarProjetosByUsuarioAction.class, BuscarProjetosByUsuarioActionHandler.class);
		bindHandler(CarregarRequisitosNoProjetoAction.class, CarregarRequisitosNoProjetoActionHandler.class);
	}
	
	private void configureProduto()
	{
		bindHandler(SalvarProdutoAction.class, SalvarProdutoActionHandler.class);
		bindHandler(LoadProdutoAction.class, LoadProdutoActionHandler.class);
		bindHandler(BuscaTodosProdutosAction.class, BuscarTodosProdutosActionHandler.class);
	}
	
	private void configureStakeholder()
	{
		bindHandler(SalvarStakeholderAction.class, SalvarStakeholderActionHandler.class);
		bindHandler(ExcluirStakeholderAction.class, ExcluirStakeholderActionHandler.class);
		bindHandler(BuscarStakeholderAction.class, BuscarStakeholderActionHandler.class);
		bindHandler(BuscarStakeholderByIdAction.class, LoadStakeholderActionHandler.class);
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
		bindHandler(BuscarRequisitoByIdAction.class, LoadRequisitoActionHandler.class);
	}
	
}
