package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.home.HomePresenter;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.home.quadro.QuadroScrumPresenter.QuadroScrumView;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.enums.StatusRequisito;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class QuadroScrumPresenter extends Presenter<QuadroScrumView, QuadroScrumProxy>
{
	public interface QuadroScrumView extends View
	{
		void setColunas(List<ColunaQuadroScrum> colunas);
	}
	
	@ProxyCodeSplit
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	@NameToken(NameTokens.scrum)
	public interface QuadroScrumProxy extends ProxyPlace<QuadroScrumPresenter>
	{
	}
	
	private final UsuarioLogadoGatekeeper usuarioLogado;
	private final DispatchAsync dispatcher;
	
	@Inject
	public QuadroScrumPresenter(EventBus eventBus, QuadroScrumView view, QuadroScrumProxy proxy, final DispatchAsync dispatcher,
			final UsuarioLogadoGatekeeper usuarioLogado)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request)
	{
		super.prepareFromRequest(request);
		
		String id = request.getParameter(Parameters.projid, null);
		if(id == null)
		{
			getView().setColunas(new ArrayList<ColunaQuadroScrum>());
			return;
		}
		
		dispatcher.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				List<ColunaQuadroScrum> colunas = constroiColunas(result.getProjeto());
				getView().setColunas(colunas);
			}
		});
	}
	
	private List<ColunaQuadroScrum> constroiColunas(Projeto projeto)
	{
		List<ColunaQuadroScrum> colunas = new ArrayList<ColunaQuadroScrum>();
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
			colunas.add(new ColunaQuadroScrum(requisitos, sr.desc()));
		}
		return colunas;
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, HomePresenter.TYPE_SetQuadroScrumContent, this);
	}
}
