package com.geekvigarista.scrummanager.client.telas.inicio.home.projetos;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.events.projetoselecionado.ProjetoSelecionadoEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter.ListaProjetosProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.home.projetos.ListaProjetosUsuarioPresenter.ListaProjetosView;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoListResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetosByUsuarioAction;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ListaProjetosUsuarioPresenter extends Presenter<ListaProjetosView, ListaProjetosProxy>
{
	public interface ListaProjetosView extends View
	{
		void setProjetos(List<ProjetoStakeholderDTO> projetos);
		
		ProjetoCellFactory factory();
	}
	
	@ProxyCodeSplit
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface ListaProjetosProxy extends Proxy<ListaProjetosUsuarioPresenter>
	{
		
	}
	
	private final UsuarioLogadoGatekeeper usuarioLogado;
	private final DispatchAsync dispatcher;
	private final PlaceManager placemanager;
	private String idProjetoSelecionado = null;
	
	@Inject
	public ListaProjetosUsuarioPresenter(EventBus eventBus, ListaProjetosView view, ListaProjetosProxy proxy, final DispatchAsync dispatcher,
			final UsuarioLogadoGatekeeper usuarioLogado, final PlaceManager placemanager)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.usuarioLogado = usuarioLogado;
		this.placemanager = placemanager;
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		Usuario u = usuarioLogado.getUsuario();
		dispatcher.execute(new BuscarProjetosByUsuarioAction(u), new AbstractCallback<BuscarProjetoListResult>()
		{
			@Override
			public void onSuccess(BuscarProjetoListResult result)
			{
				System.out.println("ListaProjetosUsuarioPresenter.onReveal().new AbstractCallback() {...}.onSuccess() " + idProjetoSelecionado);
				getView().setProjetos(result.getProjetos());
				
				idProjetoSelecionado = placemanager.getCurrentPlaceRequest().getParameter(Parameters.projid, null);
				
				if(idProjetoSelecionado != null)
				{
					for(ProjetoStakeholderDTO proj : result.getProjetos())
					{
						if(proj.getProjeto().getId().equals(idProjetoSelecionado))
						{
							getView().factory().getSelectionModel().setSelected(proj, true);
							return;
						}
					}
				}
				else
				{
					getView().factory().getSelectionModel().setSelected(null, true);
				}
			}
		});
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
				Projeto projeto = getView().factory().getSelectionModel().getSelectedObject().getProjeto();
				getEventBus().fireEvent(new ProjetoSelecionadoEvent(projeto));
				PlaceRequest pr = new PlaceRequest(NameTokens.home).with(Parameters.projid, projeto.getId());
				placemanager.updateHistory(pr, true);
			}
		});
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, HomePresenter.TYPE_SetProjetosContent, this);
	}
}
