package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.place.ClientPlaceManager;
import com.geekvigarista.scrummanager.client.place.DefaultPlace;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.AddProdutoView;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter.CadProdutoView;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoView;
import com.geekvigarista.scrummanager.client.telas.cadastros.requisito.AddRequisitoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.requisito.AddRequisitoView;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderView;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjView;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjViewImpl;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserView;
import com.geekvigarista.scrummanager.client.telas.erros.Erro404ViewImpl;
import com.geekvigarista.scrummanager.client.telas.erros.Error404Presenter;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenu;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenuPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomeView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter.ListaProjetosProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter.ListaProjetosView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumViewImpl;
import com.geekvigarista.scrummanager.client.telas.inicio.login.LoginPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.login.LoginViewImpl;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainView;
import com.geekvigarista.scrummanager.client.telas.visao.requisito.VisualizarRequisitoPresenter;
import com.geekvigarista.scrummanager.client.telas.visao.requisito.VisualizarRequisitoView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule
{
	
	@Override
	protected void configure()
	{
		// singletons
		install(new DefaultModule(ClientPlaceManager.class));
		
		// constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
		
		bindPresenter(HomePresenter.class, HomePresenter.HomeView.class, HomeView.class, HomePresenter.HomeProxy.class);
		
		bindPresenter(MainPresenter.class, MainPresenter.MainView.class, MainView.class, MainPresenter.MainProxy.class);
		
		bindPresenter(AddUserPresenter.class, AddUserPresenter.AddUserView.class, AddUserView.class, AddUserPresenter.AddUserProxy.class);
		
		bindPresenter(AddStakeholderPresenter.class, AddStakeholderPresenter.AddStakeholderView.class, AddStakeholderView.class,
				AddStakeholderPresenter.AddStakeholderProxy.class);
		
		bindPresenter(AddProjetoPresenter.class, AddProjetoPresenter.AddProjetoView.class, AddProjetoView.class,
				AddProjetoPresenter.AddProjetoProxy.class);
		
		bindPresenter(AddRequisitoPresenter.class, AddRequisitoPresenter.AddRequisitoView.class, AddRequisitoView.class,
				AddRequisitoPresenter.AddRequisitoProxy.class);
		
		bindPresenter(VisualizarRequisitoPresenter.class, VisualizarRequisitoPresenter.VisReqView.class, VisualizarRequisitoView.class,
				VisualizarRequisitoPresenter.VisReqProxy.class);
		
		bindPresenter(LoginPresenter.class, LoginPresenter.LoginView.class, LoginViewImpl.class, LoginPresenter.LoginProxy.class);
		
		bindPresenter(MainMenuPresenter.class, MainMenuPresenter.MainMenuView.class, MainMenu.class, MainMenuPresenter.MainMenuProxy.class);
		
		bindPresenter(Error404Presenter.class, Error404Presenter.Erro404View.class, Erro404ViewImpl.class, Error404Presenter.Erro404Proxy.class);
		
		bindPresenter(ListaProjetosUsuarioPresenter.class, ListaProjetosView.class, ListaProjetosUsuarioView.class, ListaProjetosProxy.class);
		
		bindPresenter(QuadroScrumPresenter.class, QuadroScrumView.class, QuadroScrumViewImpl.class, QuadroScrumProxy.class);
		
		bindPresenter(CadastroProdutoPresenter.class, CadProdutoView.class, AddProdutoView.class, CadProdutoProxy.class);
		
		bindPresenter(AddStakToProjPresenter.class, AddStakToProjView.class, AddStakToProjViewImpl.class, AddStakToProjProxy.class);
	}
}
