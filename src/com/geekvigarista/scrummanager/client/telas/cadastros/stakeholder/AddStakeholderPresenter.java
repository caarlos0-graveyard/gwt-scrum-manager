package com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoAction;
import com.geekvigarista.scrummanager.shared.commands.projeto.load.LoadProjetoResult;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderResult;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddStakeholderPresenter extends Presenter<AddStakeholderPresenter.AddStakeholderView, AddStakeholderPresenter.AddStakeholderProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addstak)
	public interface AddStakeholderProxy extends ProxyPlace<AddStakeholderPresenter>
	{
	}
	
	public interface AddStakeholderView extends View
	{
		ListBox getUsuarios();
		
		ListBox getProjetos();
		
		ListBox getPapeis();
		
		HasValue<String> getNome();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
	}
	
	private final DispatchAsync dispatcher;
	private Stakeholder stakeholder;
	private Projeto projeto;
	
	@Inject
	public AddStakeholderPresenter(final EventBus eventBus, final AddStakeholderView view, final AddStakeholderProxy proxy,
			final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		
		for(PapelStakeholder p : PapelStakeholder.values())
		{
			getView().getPapeis().addItem(p.desc(), p.name());
		}
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		getView().getBtSalvar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				doSalvar();
			}
		});
	}
	
	private void doSalvar()
	{
		Stakeholder stakeholder = new Stakeholder();
		stakeholder.setPapel(PapelStakeholder.values()[getView().getPapeis().getSelectedIndex()]);
		
		// TODO conversao
		dispatcher.execute(new SalvarStakeholderAction(stakeholder), new AbstractCallback<SalvarStakeholderResult>()
		{
			@Override
			public void onSuccess(SalvarStakeholderResult result)
			{
				// TODO Auto-generated method stub
			}
		});
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
		doLoadProjetoFromRequest(request.getParameter(Parameters.projid, null));
		doLoadStakeholderFromRequest(request.getParameter(Parameters.stakid, null));
	}
	
	public void doLoadStakeholderFromRequest(String stakid)
	{
		if(stakid == null)
			return; //FIXME tratar essa pica aqui
			
		// TODO loadStakeholder action e cia.
	}
	
	public void doLoadProjetoFromRequest(String projid)
	{
		if(projid == null)
			return; //FIXME tratar essa pica aqui
			
		dispatcher.execute(new LoadProjetoAction(projid), new AbstractCallback<LoadProjetoResult>()
		{
			@Override
			public void onSuccess(LoadProjetoResult result)
			{
				setProjeto(result.getProjeto());
				doNovo();
			}
		});
	}
	
	public void doNovo()
	{
		// TODO
	}
	
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
	
	public void setStakeholder(Stakeholder stakeholder)
	{
		this.stakeholder = stakeholder;
	}
	
	public Projeto getProjeto()
	{
		return projeto;
	}
	
	public void setProjeto(Projeto projeto)
	{
		this.projeto = projeto;
	}
}
