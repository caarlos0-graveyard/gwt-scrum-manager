package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.geekvigarista.scrummanager.client.converters.interfaces.IUsuarioConverter;
import com.geekvigarista.scrummanager.client.gatekeeper.UsuarioLogadoGatekeeper;
import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.AddUserProxy;
import com.geekvigarista.scrummanager.client.telas.cadastros.usuario.AddUserPresenter.AddUserView;
import com.geekvigarista.scrummanager.client.telas.commons.AbstractCallback;
import com.geekvigarista.scrummanager.client.telas.commons.msgbox.MsgBox;
import com.geekvigarista.scrummanager.client.telas.inicio.main.MainPresenter;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioAction;
import com.geekvigarista.scrummanager.shared.commands.usuario.salvar.SalvarUsuarioResult;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AddUserPresenter extends Presenter<AddUserView, AddUserProxy>
{
	
	@ProxyCodeSplit
	@NameToken(NameTokens.adduser)
	@UseGatekeeper(UsuarioLogadoGatekeeper.class)
	public interface AddUserProxy extends ProxyPlace<AddUserPresenter>
	{
	}
	
	public interface AddUserView extends View
	{
		TextBox getNome();
		
		HasValue<String> getLogin();
		
		HasValue<String> getSenha();
		
		HasValue<String> getConfSenha();
		
		HasValue<Boolean> getAdministrador();
		
		HasClickHandlers getBtSalvar();
		
		HasClickHandlers getBtCancelar();
		
	}
	
	private final DispatchAsync dispatcher;
	private final IUsuarioConverter converter;
	private Usuario usuario;
	
	@Inject
	public AddUserPresenter(final EventBus eventBus, final AddUserView view, final AddUserProxy proxy, final DispatchAsync dispatcher,
			final IUsuarioConverter converter)
	{
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.converter = converter;
		this.usuario = new Usuario();
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
		Usuario usuario = converter.convert(getUsuario(), getView());
		dispatcher.execute(new SalvarUsuarioAction(usuario), new AbstractCallback<SalvarUsuarioResult>()
		{
			@Override
			public void onSuccess(SalvarUsuarioResult result)
			{
				if(result.getErros() == null || result.getErros().isEmpty())
				{
					String msg = "Usuario " + result.getResponse().getNome() + " salvo com sucesso";
					new MsgBox(msg, false);
					setUsuario(result.getResponse());
				}
				else
				{
					new MsgBox(result.getErros(), true);
				}
			}
		});
	}
	
	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}
	
	public Usuario getUsuario()
	{
		return usuario;
	}
	
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
		converter.updateView(usuario, getView());
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		getView().getNome().setFocus(true);
	}
}
