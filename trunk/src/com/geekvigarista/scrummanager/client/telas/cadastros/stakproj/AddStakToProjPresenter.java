package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.cadastros.interfaces.SimpleCadPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakproj.AddStakToProjPresenter.AddStakToProjView;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderListResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.DispatchRequest;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddStakToProjPresenter extends SimpleCadPresenter<AddStakToProjView, AddStakToProjProxy>
{
	public interface AddStakToProjView extends View
	{
		void updateStakes(List<Stakeholder> stakeholders);
		
		List<Stakeholder> getSelecionados();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.addstaktoproj)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface AddStakToProjProxy extends ProxyPlace<AddStakToProjPresenter>
	{
	}
	
	private final DispatchAsync dispatch;
	private final PlaceManager placeManager;
	private Projeto projeto;
	private List<Stakeholder> stakeholders;
	private List<Stakeholder> stakeholdersSelecionados;
	private DispatchRequest loadProjeto;
	
	@Inject
	public AddStakToProjPresenter(EventBus eventBus, AddStakToProjView view, AddStakToProjProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatch)
	{
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
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
		String id = request.getParameter(Parameters.projid, null);
		if(id == null)
		{
			return;
		}
		
		doLoadProjeto(id);
	}
	
	private void doLoadProjeto(String id)
	{
		loadProjeto = dispatch.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				stakeholdersSelecionados = projeto.getStakeholders();
			}
		});
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		dispatch.execute(new BuscarStakeholderAction(""), new AbstractCallback<BuscarStakeholderListResult>()
		{
			@Override
			public void onSuccess(BuscarStakeholderListResult result)
			{
				Window.alert(result.getResponse().size()+"");
				setStakeholders(result.getResponse());
			}
		});
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
	
	public List<Stakeholder> getStakeholders()
	{
		return stakeholders;
	}
	
	public void setStakeholders(List<Stakeholder> stakeholders)
	{
		System.out.println(stakeholders);
		this.stakeholders = stakeholders;
	}
	
	public List<Stakeholder> getStakeholdersSelecionados()
	{
		return stakeholdersSelecionados;
	}
	
	public void setStakeholdersSelecionados(List<Stakeholder> stakeholdersSelecionados)
	{
		this.stakeholdersSelecionados = stakeholdersSelecionados;
	}
	
}
