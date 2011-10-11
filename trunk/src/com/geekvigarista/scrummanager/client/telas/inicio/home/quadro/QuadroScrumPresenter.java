package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStartEvent;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.abrirmodalencaminhar.AbrirModalEncaminharEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.abrirmodalencaminhar.AbrirModalEncaminharEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.events.encaminhar.EncaminharEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.encaminhar.EncaminharEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.events.projetoselecionado.ProjetoSelecionadoEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.projetoselecionado.ProjetoSelecionadoEventHandler;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumView;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.coluna.ColunaQuadroScrum;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.modalencaminhar.ModalEncaminhar;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.CarregarRequisitosNoProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.salvar.SalvarRequisitoResult;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderListResult;
import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.utils.EncaminharUtil;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class QuadroScrumPresenter extends Presenter<QuadroScrumView, QuadroScrumProxy> implements ProjetoSelecionadoEventHandler,
		EncaminharEventHandler, AbrirModalEncaminharEventHandler
{
	public interface QuadroScrumView extends View
	{
		void setColunas(List<ColunaQuadroScrum> colunas);
	}
	
	@ProxyCodeSplit
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface QuadroScrumProxy extends Proxy<QuadroScrumPresenter>
	{
	}
	
	private final UsuarioLogadoGatekeeper usuarioLogado;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	
	@Inject
	public QuadroScrumPresenter(EventBus eventBus, QuadroScrumView view, QuadroScrumProxy proxy, final DispatchAsync dispatcher,
			final UsuarioLogadoGatekeeper usuarioLogado, final PlaceManager placeManager)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.usuarioLogado = usuarioLogado;
		this.placeManager = placeManager;
		getView().setColunas(null);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		
		final String id = request.getParameter(Parameters.projid, null);
		if(id == null)
		{
			getView().setColunas(null);
			return;
		}
		
		new AbstractCallback<LoadProjetoResult>()
		{
			
			@Override
			protected void callService(AsyncCallback<LoadProjetoResult> asyncCallback)
			{
				dispatcher.execute(new LoadProjetoAction(id), asyncCallback);
			}
			
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				constroiColunas(result.getProjeto());
			}
		}.goDefault();
		
	}
	
	@Override
	protected void onReset()
	{
		super.onReset();
		
	}
	
	private void constroiColunas(final Projeto projeto)
	{
		if(projeto == null || projeto.getId() == null)
		{
			getView().setColunas(null); // vai add o label de nenhum proj. selecionado..
			return;
		}
		
		new AbstractCallback<LoadProjetoResult>()
		{
			
			@Override
			protected void callService(AsyncCallback<LoadProjetoResult> asyncCallback)
			{
				dispatcher.execute(new CarregarRequisitosNoProjetoAction(projeto), asyncCallback);
			}
			
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				List<ColunaQuadroScrum> colunas = new ArrayList<ColunaQuadroScrum>();
				for(StatusRequisito sr : StatusRequisito.values())
				{
					List<Requisito> requisitos = new ArrayList<Requisito>();
					for(Requisito r : result.getProjeto().getRequisitos())
					{
						if(r.getEncaminhamentos().get(r.getEncaminhamentos().size() - 1).getStatus().equals(sr))
						{
							requisitos.add(r);
						}
					}
					colunas.add(new ColunaQuadroScrum(requisitos, sr.desc(), getEventBus(), usuarioLogado.getUsuario()));
				}
				getView().setColunas(colunas);
				getEventBus().fireEvent(new LoadingStopEvent());
			}
		}.goDefault();
		
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, HomePresenter.TYPE_SetQuadroScrumContent, this);
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
	}
	
	@Override
	@ProxyEvent
	public void onProjetoSelecionado(ProjetoSelecionadoEvent event)
	{
		constroiColunas(event.getProjeto());
	}
	
	@Override
	@ProxyEvent
	public void onEncaminharRequisito(EncaminharEvent event)
	{
		getEventBus().fireEvent(new LoadingStartEvent());
		final Requisito requisito = EncaminharUtil.encaminhar(event.getRequisito(), event.getStakeholder(), event.getDescricao(), event.getTempoGasto(),
				event.getAcao());
		
		new AbstractCallback<SalvarRequisitoResult>()
		{

			@Override
			protected void callService(AsyncCallback<SalvarRequisitoResult> asyncCallback)
			{
				dispatcher.execute(new SalvarRequisitoAction(requisito, requisito.getProjeto()), asyncCallback);
			}
			
			@Override
			public void onSuccess(SalvarRequisitoResult result)
			{
				if(placeManager.getCurrentPlaceRequest().getNameToken().equals(NameTokens.visreq))
				{
					placeManager.revealCurrentPlace();
				}
				else
				{
					constroiColunas(result.getProjeto());
				}
			}
		}.goDefault();
		
	}
	
	@Override
	@ProxyEvent
	public void onAbrirModalEncaminharRequisito(AbrirModalEncaminharEvent event)
	{
		getEventBus().fireEvent(new LoadingStartEvent());
		final AcaoEncaminhar acao = event.getAcao();
		final Requisito requisito = event.getRequisito();
		
		new AbstractCallback<BuscarStakeholderListResult>()
		{
			
			@Override
			protected void callService(AsyncCallback<BuscarStakeholderListResult> asyncCallback)
			{
				dispatcher.execute(new BuscarStakeholderAction(null), asyncCallback);
			}
			
			@Override
			public void onSuccess(BuscarStakeholderListResult result)
			{
				ModalEncaminhar me = new ModalEncaminhar(result.getResponse(), getEventBus(), acao, requisito);
				me.show();
				getEventBus().fireEvent(new LoadingStopEvent());
			}
		}.goDefault();
	}
}
