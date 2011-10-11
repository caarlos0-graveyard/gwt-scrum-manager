package com.geekvigarista.scrummanager.client.telas.visao.requisito;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.events.LoadingStopEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.events.abrirmodalencaminhar.AbrirModalEncaminharEvent;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.requisito.buscar.BuscarRequisitoByIdAction;
import com.geekvigarista.scrummanager.shared.commands.requisito.buscar.BuscarRequisitoObjResult;
import com.geekvigarista.scrummanager.shared.enums.AcaoEncaminhar;
import com.geekvigarista.scrummanager.shared.utils.EncaminharUtil;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class VisualizarRequisitoPresenter extends Presenter<VisualizarRequisitoPresenter.VisReqView, VisualizarRequisitoPresenter.VisReqProxy>
{
	/*
	 * inner interfaces classe
	 */
	
	@ProxyCodeSplit
	@NameToken(NameTokens.visreq)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface VisReqProxy extends ProxyPlace<VisualizarRequisitoPresenter>
	{
	}
	
	public interface VisReqView extends View
	{
		Label titulo();
		
		Label prioridade();
		
		DateLabel dataCadastro();
		
		Label tempoEstimado();
		
		Label tempoGasto();
		
		Label descricao();
		
		CellTable<Encaminhamento> encaminhamentosAnteriores();
		
		void setData(List<Encaminhamento> encaminhamentos);
		
		Widget asWidget();
		
		Button getBtVoltar();
		
		Button getBtAvancar();
	}
	
	/*
	 * Atributos
	 */
	
	private Requisito requisito;
	private final DispatchAsync dispatcher;
	private EventBus eventBus;
	
	/*
	 * Construtores
	 */
	
	
	@Inject
	public VisualizarRequisitoPresenter(final EventBus eventBus, final VisReqView view, final VisReqProxy proxy, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.eventBus = eventBus;
	}
	
	public void configuraBotoes()
	{
		switch(EncaminharUtil.getUltimoEncaminhamento(requisito).getStatus())
		{
			case AGUARDANDO:
				getView().getBtVoltar().setEnabled(false);
				break;
			
			case CONCLUIDO:
				getView().getBtAvancar().setEnabled(false);
				break;
			
			default:
				getView().getBtAvancar().setEnabled(true);
				getView().getBtVoltar().setEnabled(true);
				break;
		}
	}
	
	/*
	 * METHODS
	 */
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	public void onBind()
	{
		getView().getBtAvancar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventBus.fireEvent(new AbrirModalEncaminharEvent(AcaoEncaminhar.AVANCAR, requisito));
			}
		});
		
		getView().getBtVoltar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventBus.fireEvent(new AbrirModalEncaminharEvent(AcaoEncaminhar.VOLTAR, requisito));
			}
		});
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		String id = request.getParameter(Parameters.reqid, null);
		if(id != null)
		{
			doLoadRequisito(id);
		}
		else
		{
			setRequisito(null);
		}
	}
	
	public void doLoadRequisito(String id)
	{
		dispatcher.execute(new BuscarRequisitoByIdAction(id), new AbstractCallback<BuscarRequisitoObjResult>()
		{
			@Override
			public void onSuccess(BuscarRequisitoObjResult result)
			{
				setRequisito(result.getResponse());
			}
		});
	}
	
	/*
	 * GETTERS E SETTER
	 */
	public Requisito getRequisito()
	{
		return requisito;
	}
	
	public void setRequisito(Requisito requisito)
	{
		this.requisito = requisito;
		getView().titulo().setText(requisito.getTitulo());
		getView().descricao().setText(requisito.getDescricao());
		getView().dataCadastro().setValue(requisito.getDataCadastro());
		getView().prioridade().setText(requisito.getPrioridade().desc());
		
		getView().tempoEstimado().setText(Integer.toString(requisito.getTempoEstimado()) + "horas");
		getView().tempoGasto().setText(Integer.toString(requisito.getTempoTotal()) + "horas");
		
		getView().setData(requisito.getEncaminhamentos());
		
		configuraBotoes();
		
		getEventBus().fireEvent(new LoadingStopEvent());
	}
	
}
