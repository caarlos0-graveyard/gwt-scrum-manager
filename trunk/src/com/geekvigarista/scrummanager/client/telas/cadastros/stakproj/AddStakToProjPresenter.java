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
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.salvar.SalvarProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderListResult;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
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
		
		HasClickHandlers btAvancar();
		
		HasClickHandlers btVoltar();
		
		List<Stakeholder> getSelecionados();
		
		void setSelecionados(List<Stakeholder> stakes);
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
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		dispatch.execute(new BuscarStakeholderAction(""), new AbstractCallback<BuscarStakeholderListResult>()
		{
			@Override
			public void onSuccess(BuscarStakeholderListResult result)
			{
				setStakeholders(result.getResponse());
			}
		});
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		getView().btVoltar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doVoltar();
			}
		});
		getView().btAvancar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doSalvar();
			}
		});
	}
	
	@Override
	public void doSalvar()
	{
		List<Stakeholder> stakeSelecionados = getView().getSelecionados();
		projeto.setStakeholders(stakeSelecionados);
		
		dispatch.execute(new SalvarProjetoAction(projeto), new AbstractCallback<SalvarProjetoResult>()
		{
			@Override
			public void onSuccess(SalvarProjetoResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					setProjeto(result.getResponse());
					
					String msg = "Projeto " + result.getResponse().getNome() + " salvo com sucesso";
					new MsgBox(msg, false);
					placeManager.revealPlace(new PlaceRequest(NameTokens.scrum).with(Parameters.projid, result.getResponse().getId()));
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		});
		
	}
	
	public void doVoltar()
	{
		PlaceRequest pr = new PlaceRequest(NameTokens.addreq).with(Parameters.projid, projeto != null ? projeto.getId() : "null");
		placeManager.revealPlace(pr);
	}
	
	private void doLoadProjeto(String id)
	{
		loadProjeto = dispatch.execute(new LoadProjetoAction(id), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
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
		this.stakeholders = stakeholders;
		getView().updateStakes(stakeholders);
		new Timer()
		{
			@Override
			public void run()
			{
				if(!loadProjeto.isPending())
				{
					getView().setSelecionados(getProjeto().getStakeholders());
					cancel();
				}
			}
		}.scheduleRepeating(250);
	}
}
