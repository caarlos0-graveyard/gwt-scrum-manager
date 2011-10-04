package com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu;

import javax.inject.Inject;

import com.geekvigarista.scrummanager.client.events.LogoutEvent;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenuPresenter.MainMenuProxy;
import com.geekvigarista.scrummanager.client.telas.inicio.componentes.mainmenu.MainMenuPresenter.MainMenuView;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.usuario.buscar.BuscarUsuarioObjResult;
import com.geekvigarista.scrummanager.shared.commands.usuario.login.LogoutUsuarioAction;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItem;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class MainMenuPresenter extends Presenter<MainMenuView, MainMenuProxy>
{
	public interface MainMenuView extends View
	{
		MenuItem usuario();
		
		MenuItem stakeholder();
		
		MenuItem projeto();
		
		MenuItem inicio();
		
		MenuItem produto();
		
		MenuItem sair();
	}
	
	@ProxyCodeSplit
	public interface MainMenuProxy extends Proxy<MainMenuPresenter>
	{
	}
	
	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;
	
	@Inject
	public MainMenuPresenter(EventBus eventBus, MainMenuView view, MainMenuProxy proxy, final PlaceManager placeManager, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		
		getView().produto().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				goToPlace(NameTokens.addprod);
			}
		});
		
		getView().projeto().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				goToPlace(NameTokens.addproj);
			}
		});
		
		getView().usuario().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				goToPlace(NameTokens.adduser);
			}
		});
		
		getView().stakeholder().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				goToPlace(NameTokens.addstak);
			}
		});
		
		getView().inicio().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				goToPlace(NameTokens.home);
			}
		});
		
		getView().sair().setCommand(new Command()
		{
			@Override
			public void execute()
			{
				doLogout();
			}
		});
	}
	
	private void doLogout()
	{
		dispatcher.execute(new LogoutUsuarioAction(), new AbstractCallback<BuscarUsuarioObjResult>()
		{
			@Override
			public void onSuccess(BuscarUsuarioObjResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					getEventBus().fireEvent(new LogoutEvent());
				}else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		});
	}
	
	private void goToPlace(String place)
	{
		PlaceRequest pr = new PlaceRequest(place);
		placeManager.revealPlace(pr);
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMenuContent, this);
	}
}
