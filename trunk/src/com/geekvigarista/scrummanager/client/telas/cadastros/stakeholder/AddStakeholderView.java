package com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddStakeholderView extends ViewImpl implements AddStakeholderPresenter.AddStakeholderView
{
	
	private static AddStakeholderViewUiBinder uiBinder = GWT.create(AddStakeholderViewUiBinder.class);
	
	interface AddStakeholderViewUiBinder extends UiBinder<Widget, AddStakeholderView>
	{
	}
	
	@UiField
	HTMLPanel conteudo;
	@UiField
	TextBox nome;
	@UiField
	ListBox papelStakeholder;
	@UiField
	ListBox usuario;
	@UiField
	ListBox projeto;
	@UiField
	Hyperlink btCancelar;
	@UiField
	Button btSalvar;
	
	public AddStakeholderView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public ListBox getUsuarios()
	{
		return usuario;
	}
	
	@Override
	public ListBox getProjetos()
	{
		return projeto;
	}
	
	@Override
	public ListBox getPapeis()
	{
		return papelStakeholder;
	}
	
	@Override
	public HasValue<String> getNome()
	{
		return nome;
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
	
}
