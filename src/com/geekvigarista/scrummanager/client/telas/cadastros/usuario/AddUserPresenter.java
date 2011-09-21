package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.geekvigarista.scrummanager.client.converters.UsuarioConverter;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.AddUserProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.AddUserView;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddUserPresenter extends Presenter<AddUserView, AddUserProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.adduser)
	public interface AddUserProxy extends ProxyPlace<AddUserPresenter>
	{
	}
	
	public interface AddUserView extends View
	{
		HasValue<String> getNome();
		
		HasValue<String> getLogin();
		
		HasValue<String> getSenha();
		
		HasValue<String> getConfSenha();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
		
	}
	
	private final DispatchAsync dispatcher;
	
	@Inject
	public AddUserPresenter(final EventBus eventBus, final AddUserView view, final AddUserProxy proxy, final DispatchAsync dispatcher)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		registerHandler(getView().getBtSalvar().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				salvar();
			}
		}));
	}
	
	private void salvar()
	{
		Usuario usuario = UsuarioConverter.convert(getView().getNome(), getView().getLogin(), getView().getSenha());
		dispatcher.execute(new SalvarUsuarioAction(usuario), new AsyncCallback<SalvarUsuarioResult>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("FAIL");
				caught.printStackTrace(); //TODO
			}
			
			@Override
			public void onSuccess(SalvarUsuarioResult result)
			{
				Window.alert("LOL " + result.getResponse().getNome()); //TODO
			}
		});
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
}
