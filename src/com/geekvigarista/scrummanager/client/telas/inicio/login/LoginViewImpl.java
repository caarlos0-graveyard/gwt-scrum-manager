package com.geekvigarista.scrummanager.client.telas.inicio.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class LoginViewImpl extends ViewImpl implements LoginPresenter.LoginView
{
	
	private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);
	
	interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl>
	{
	}
	
	public LoginViewImpl()
	{
		uiBinder.createAndBindUi(this);
	}
	
	@UiField
	HTMLPanel conteudo;
	
	@UiField
	TextBox login;
	
	@UiField
	TextBox passwd;
	
	@UiField
	Button btlogin;
	
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
