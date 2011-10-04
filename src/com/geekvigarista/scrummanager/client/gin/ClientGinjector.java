package com.geekvigarista.scrummanager.client.gin;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProdutoConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IProjetoConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IRequisitoConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IStakeholderConverter;
import com.geekvigarista.scrummanager.client.converters.interfaces.IUsuarioConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.telas.cadastros.produto.CadastroProdutoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.one.AddProjetoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.requisito.AddRequisitoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter;
import com.geekvigarista.scrummanager.client.telas.erros.Error404Presenter;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenuPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.login.LoginPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.client.telas.visao.requisito.VisualizarRequisitoPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({DispatchAsyncModule.class, ClientModule.class, ViewBeanConverterModule.class})
public interface ClientGinjector extends Ginjector
{
	PlaceManager getPlaceManager();
	
	EventBus getEventBus();
	
	/*
	 * Providers das presenters
	 */
	Provider<MainPresenter> getMainPresenter();
	
	AsyncProvider<HomePresenter> getHomePresenter();
	
	AsyncProvider<AddUserPresenter> getAddUserPresenter();
	
	AsyncProvider<AddStakeholderPresenter> getAddStakeholderPresenter();
	
	AsyncProvider<AddProjetoPresenter> getAddProjetoPresenter();
	
	AsyncProvider<AddRequisitoPresenter> getAddRequisitoPresenter();
	
	AsyncProvider<VisualizarRequisitoPresenter> getVisualizarRequisitoPresenter();
	
	AsyncProvider<LoginPresenter> getLoginPresenter();
	
	AsyncProvider<MainMenuPresenter> getMainMenuPresenter();
	
	Provider<Error404Presenter> getError404Presenter();
	
	AsyncProvider<QuadroScrumPresenter> getQuadroScrumPresenter();
	
	AsyncProvider<ListaProjetosUsuarioPresenter> getListaProjetosUsuarioPresenter();
	
	AsyncProvider<CadastroProdutoPresenter> getCadastroProdutoPresenter();
	
	/*
	 * Providers de gatekeepers
	 */
	UsuarioLogadoGatekeeper getUsuarioLogadoGatekeeper();
	
	/*
	 * providers dos conversores
	 */
	IProjetoConverter getProjetoConverter();
	
	IStakeholderConverter getStakeholderConverter();
	
	IUsuarioConverter getUsuarioConverter();
	
	IProdutoConverter getProdutoConverter();
	
	IRequisitoConverter getIRequisitoConverter();
}
