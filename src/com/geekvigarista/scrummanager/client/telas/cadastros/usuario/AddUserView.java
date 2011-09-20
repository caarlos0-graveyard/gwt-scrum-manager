package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddUserView extends ViewImpl implements AddUserPresenter.AddUserView
{
	
	private static AddUserViewUiBinder uiBinder = GWT.create(AddUserViewUiBinder.class);
	
	interface AddUserViewUiBinder extends UiBinder<Widget, AddUserView>
	{
	}
	
	@UiField
	HTMLPanel conteudo;
	@UiField
	TextBox nome;
	@UiField
	TextBox login;
	@UiField
	TextBox senha;
	@UiField
	TextBox confSenha;
	@UiField
	Hyperlink btCancelar;
	@UiField
	Button btSalvar;
	
	public AddUserView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	@Override
	public HasValue<String> getNome()
	{
		return nome;
	}
	
	@Override
	public HasValue<String> getLogin()
	{
		return login;
	}
	
	@Override
	public HasValue<String> getSenha()
	{
		return senha;
	}
	
	@Override
	public HasValue<String> getConfSenha()
	{
		return confSenha;
	}
	
	@Override
	public HasClickHandlers getBtSalvar()
	{
		return btSalvar;
	}
	
	@Override
	public HasClickHandlers getBtCancelar()
	{
		return btCancelar;
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
}
