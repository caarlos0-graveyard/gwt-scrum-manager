package com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderByIdAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.buscar.BuscarStakeholderObjResult;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderAction;
import com.geekvigarista.scrummanager.shared.commands.stakeholder.salvar.SalvarStakeholderResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioListResult;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
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
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddStakeholderPresenter extends Presenter<AddStakeholderPresenter.AddStakeholderView, AddStakeholderPresenter.AddStakeholderProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addstak)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface AddStakeholderProxy extends ProxyPlace<AddStakeholderPresenter>
	{
	}
	
	public interface AddStakeholderView extends View
	{
		ListBox getUsuarios();
		
		ListBox getPapeis();
		
		HasValue<String> getNome();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
	}
	
	private final DispatchAsync dispatcher;
	private Stakeholder stakeholder;
	private List<Usuario> usuariosSistema;
	
	@Inject
	public AddStakeholderPresenter(final EventBus eventBus, final AddStakeholderView view, final AddStakeholderProxy proxy,
			final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		loadPapeis();
		loadUsuarios();
	}
	
	private void loadPapeis()
	{
		if(getView().getPapeis().getItemCount() > 0)
			return;
		for(PapelStakeholder p : PapelStakeholder.values())
		{
			getView().getPapeis().addItem(p.desc());
		}
	}
	
	private void loadUsuarios()
	{
		if(usuariosSistema != null)
		{
			return;
		}
		
		dispatcher.execute(new BuscarUsuarioAction(""), new AbstractCallback<BuscarUsuarioListResult>()
		{
			@Override
			public void onSuccess(BuscarUsuarioListResult result)
			{
				usuariosSistema = result.getResponse();
				for(Usuario usuario : usuariosSistema)
				{
					getView().getUsuarios().addItem(usuario.getNome());
					populaCadastro();
				}
			}
		});
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
		stakeholder.setUsuario(usuariosSistema.get(getView().getUsuarios().getSelectedIndex()));
		stakeholder.setNome(getView().getNome().getValue());
		if(getStakeholder() != null && getStakeholder().getId() != null)
			stakeholder.setId(getStakeholder().getId());
		
		dispatcher.execute(new SalvarStakeholderAction(stakeholder), new AbstractCallback<SalvarStakeholderResult>()
		{
			@Override
			public void onSuccess(SalvarStakeholderResult result)
			{
				setStakeholder(result.getResponse());
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
		doLoadStakeholderFromRequest(request.getParameter(Parameters.stakid, null));
	}
	
	public void doLoadStakeholderFromRequest(String stakid)
	{
		if(stakid == null)
			return; //FIXME tratar essa pica aqui
			
		dispatcher.execute(new BuscarStakeholderByIdAction(stakid), new AbstractCallback<BuscarStakeholderObjResult>()
		{
			@Override
			public void onSuccess(BuscarStakeholderObjResult result)
			{
				setStakeholder(result.getResponse());
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
	
	public void populaCadastro()
	{
		if(stakeholder == null)
			return;
		getView().getNome().setValue(stakeholder.getNome());
		getView().getPapeis().setSelectedIndex(stakeholder.getPapel().ordinal());
		getView().getUsuarios().setSelectedIndex(usuariosSistema.indexOf(stakeholder.getUsuario()));
	}
}
