package com.geekvigarista.scrummanager.client.telas.inicio.home;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class HomePresenter extends Presenter<HomePresenter.HomeView, HomePresenter.HomeProxy>
{
	public interface HomeView extends View
	{
		void setProjetos(List<Projeto> projetos);
		
		HorizontalPanel panelScrum();
		
		ProjetoCellFactory factory();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.home)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface HomeProxy extends ProxyPlace<HomePresenter>
	{
	}
	
	private final DispatchAsync dispatcher;
	private List<Projeto> projetos;
	private final UsuarioLogadoGatekeeper usuarioLogado;
	
	@Inject
	public HomePresenter(final EventBus eventBus, final HomeView view, final HomeProxy proxy, final DispatchAsync dispatcher, final UsuarioLogadoGatekeeper usuarioLogado)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.usuarioLogado = usuarioLogado;
		controiInterface();
		System.out.println(usuarioLogado.getUsuario());
	}
	
	private void controiInterface()
	{
		// FIXME vai ter que ter o usuario logado aqui!!!
		Usuario u = null;
		
		getView().setProjetos(new ArrayList<Projeto>());
	}
	
	

	@Override
	protected void onBind()
	{
		super.onBind();
		getView().factory().getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				Projeto projeto = getView().factory().getSelectionModel().getSelectedObject();
				getView().panelScrum().clear();
				for(StatusRequisito sr : StatusRequisito.values())
				{
					List<Requisito> requisitos = new ArrayList<Requisito>();
					for(Requisito r : projeto.getRequisitos())
					{
						if(r.getEncaminhamentos().get(r.getEncaminhamentos().size() - 1).getStatus().equals(sr))
						{
							requisitos.add(r);
						}
					}
					getView().panelScrum().add(new ColunaQuadroScrum(requisitos));
				}
			}
		});
	}

	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	
}
