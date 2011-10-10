package com.geekvigarista.scrummanager.client.telas.inicio.home;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class HomePresenter extends Presenter<HomePresenter.HomeView, HomePresenter.HomeProxy>
{
	public interface HomeView extends View
	{
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.home)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface HomeProxy extends ProxyPlace<HomePresenter>
	{
	}
	
	// slots :D
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetProjetosContent = new Type<RevealContentHandler<?>>();
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetQuadroScrumContent = new Type<RevealContentHandler<?>>();
	
	private final DispatchAsync dispatcher;
	private final UsuarioLogadoGatekeeper usuarioLogado;
	private QuadroScrumPresenter quadro;
	private ListaProjetosUsuarioPresenter projetos;
	
	@Inject
	public HomePresenter(final EventBus eventBus, final HomeView view, final HomeProxy proxy, final DispatchAsync dispatcher,
			final UsuarioLogadoGatekeeper usuarioLogado, ListaProjetosUsuarioPresenter projetos, QuadroScrumPresenter quadro)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.usuarioLogado = usuarioLogado;
		this.projetos = projetos;
		this.quadro = quadro;
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		setInSlot(TYPE_SetProjetosContent, projetos);
		setInSlot(TYPE_SetQuadroScrumContent, quadro);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
	}
	
}
