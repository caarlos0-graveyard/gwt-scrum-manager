package com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder;

import java.util.List;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.converters.interfaces.IStakeholderConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.AdminGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.client.telas.cadastros.interfaces.SimpleCadPresenter;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.componentes.msgbox.MsgBox;
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
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddStakeholderPresenter extends
		SimpleCadPresenter<AddStakeholderPresenter.AddStakeholderView, AddStakeholderPresenter.AddStakeholderProxy>
{
	@ProxyCodeSplit
	@NameToken(NameTokens.addstak)
	@UseGatekeeper(AdminGatekeeper.class)
	public interface AddStakeholderProxy extends ProxyPlace<AddStakeholderPresenter>
	{
	}
	
	public interface AddStakeholderView extends View
	{
		ListBox getUsuarios();
		
		ListBox getPapeis();
		
		TextBox getNome();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
		
		HasClickHandlers getBtNovo();
	}
	
	private final DispatchAsync dispatcher;
	private final IStakeholderConverter converter;
	private Stakeholder stakeholder;
	private List<Usuario> usuariosSistema;
	private final PlaceManager placeManager;
	
	@Inject
	public AddStakeholderPresenter(final EventBus eventBus, final AddStakeholderView view, final AddStakeholderProxy proxy,
			final DispatchAsync dispatcher, final IStakeholderConverter converter, PlaceManager placeManager)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.converter = converter;
		this.placeManager = placeManager;
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		loadPapeis();
		loadUsuarios();
		getView().getNome().setFocus(true);
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
		
		new AbstractCallback<BuscarUsuarioListResult>()
		{
			
			@Override
			protected void callService(AsyncCallback<BuscarUsuarioListResult> asyncCallback)
			{
				dispatcher.execute(new BuscarUsuarioAction(""), asyncCallback);
			}
			
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
			
		}.goDefault();
		
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		//botoes
		registerHandler(getView().getBtSalvar().addClickHandler(salvarHandler));
		registerHandler(getView().getBtCancelar().addClickHandler(cancelarHandler));
		registerHandler(getView().getBtNovo().addClickHandler(novoHandler));
		//campos
		registerHandler(getView().getNome().addKeyUpHandler(salvarHandler));
	}
	
	@Override
	public void doSalvar()
	{
		
		final Stakeholder stakeholderConvertido = converter.convert(getStakeholder(), getView(), usuariosSistema);
		
		new AbstractCallback<SalvarStakeholderResult>()
		{
			@Override
			protected void callService(AsyncCallback<SalvarStakeholderResult> asyncCallback)
			{
				dispatcher.execute(new SalvarStakeholderAction(stakeholderConvertido), asyncCallback);
			}
			
			@Override
			public void onSuccess(SalvarStakeholderResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					setStakeholder(result.getResponse());
					
					String msg = "Stakeholder " + stakeholder.getNome() + " salvo com sucesso";
					new MsgBox(msg, false);
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		}.goDefault();
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
	
	public void doLoadStakeholderFromRequest(final String stakid)
	{
		if(stakid == null)
			return;
		new AbstractCallback<BuscarStakeholderObjResult>()
		{
			@Override
			protected void callService(AsyncCallback<BuscarStakeholderObjResult> asyncCallback)
			{
				dispatcher.execute(new BuscarStakeholderByIdAction(stakid), asyncCallback);
			}
			
			@Override
			public void onSuccess(BuscarStakeholderObjResult result)
			{
				setStakeholder(result.getResponse());
			}
		}.goDefault();
	}
	
	@Override
	public void doNovo()
	{
		setStakeholder(new Stakeholder());
	}
	
	@Override
	public void doCancelar()
	{
		placeManager.revealPlace(new PlaceRequest(NameTokens.home));
	}
	
	public Stakeholder getStakeholder()
	{
		return stakeholder;
	}
	
	public void setStakeholder(Stakeholder stakeholder)
	{
		if(stakeholder == null)
		{
			this.stakeholder = new Stakeholder();
		}
		else
		{
			this.stakeholder = stakeholder;
		}
		converter.updateView(stakeholder, getView(), usuariosSistema);
		getView().getNome().setFocus(true);
	}
	
	public void populaCadastro()
	{
		converter.updateView(getStakeholder(), getView(), usuariosSistema);
	}
	
	@Override
	public void doExcluir()
	{
		
	}
}
