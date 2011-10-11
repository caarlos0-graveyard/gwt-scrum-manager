package com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.events.updatesearchinput.UpdateSearchBoxValueEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca.ResultadoBuscaPresenter.ResultadoBuscaProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.resultadobusca.ResultadoBuscaPresenter.ResultadoBuscaView;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoLikeAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.BuscarProjetoListResult;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ResultadoBuscaPresenter extends Presenter<ResultadoBuscaView, ResultadoBuscaProxy>
{
	
	public interface ResultadoBuscaView extends View
	{
		void setData(List<Projeto> lista);
		
		SingleSelectionModel<Projeto> geetSelectionModel();
		
	}
	
	private final PlaceManager placemanager;
	private final UsuarioLogadoGatekeeper gatekeeper;
	private final DispatchAsync dispatcher;
	
	@ProxyCodeSplit
	@NameToken(NameTokens.busca)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface ResultadoBuscaProxy extends ProxyPlace<ResultadoBuscaPresenter>
	{
		
	}
	
	@Inject
	public ResultadoBuscaPresenter(EventBus eventBus, ResultadoBuscaView view, ResultadoBuscaProxy proxy, final PlaceManager placemanager,
			final DispatchAsync dispatcher, final UsuarioLogadoGatekeeper gatekeeper)
	{
		super(eventBus, view, proxy);
		this.placemanager = placemanager;
		this.dispatcher = dispatcher;
		this.gatekeeper = gatekeeper;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		getView().geetSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				placemanager.revealPlace(new PlaceRequest(NameTokens.addproj).with(Parameters.projid, getView().geetSelectionModel()
						.getSelectedObject().getId()));
			}
		});
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		final String param = request.getParameter("q", "");
		
		new AbstractCallback<BuscarProjetoListResult>()
		{
			@Override
			public void onSuccess(BuscarProjetoListResult result)
			{
				List<Projeto> projetos = new ArrayList<Projeto>();
				for(ProjetoStakeholderDTO dto : result.getProjetos())
				{
					projetos.add(dto.getProjeto());
				}
				System.out.println(projetos.size());
				getView().setData(projetos);
				getEventBus().fireEvent(new UpdateSearchBoxValueEvent(param));
			}
			
			@Override
			protected void callService(AsyncCallback<BuscarProjetoListResult> asyncCallback)
			{
				Usuario usuario = gatekeeper.getUsuario();
				System.out.println(usuario.getNome());
				dispatcher.execute(new BuscarProjetoLikeAction(param, usuario), asyncCallback);
			}
		}.goDefault();
	}
}
