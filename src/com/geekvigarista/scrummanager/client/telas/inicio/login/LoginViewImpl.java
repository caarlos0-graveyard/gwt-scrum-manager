package com.geekvigarista.scrummanager.client.telas.inicio.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class LoginViewImpl extends ViewImpl implements LoginPresenter.LoginView
{
	
	private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);
	
	interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl>
	{
	}
	
	@UiField
	HTMLPanel conteudo;
	
	@UiField
	TextBox login;
	
	@UiField
	TextBox passwd;
	
	@UiField
	Button btlogin;
	
	@UiField
	VerticalPanel vpTudo;
	
	public LoginViewImpl()
	{
		uiBinder.createAndBindUi(this);
		vpTudo.setHeight(Window.getClientHeight() + "px");
		Window.addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(ResizeEvent event)
			{
				vpTudo.setHeight(Window.getClientHeight() + "px");
			}
		});
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public TextBox login()
	{
		return login;
	}
	
	@Override
	public TextBox passwd()
	{
		return passwd;
	}
	
	@Override
	public HasClickHandlers btlogin()
	{
		return btlogin;
	}
	
}
