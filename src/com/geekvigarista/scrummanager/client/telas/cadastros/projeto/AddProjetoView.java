package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import java.util.Date;

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
import com.google.gwt.user.datepicker.client.DateBox;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddProjetoView extends ViewImpl implements AddProjetoPresenter.AddProjetoView
{
	
	private static AddProjetoViewUiBinder uiBinder = GWT.create(AddProjetoViewUiBinder.class);
	
	interface AddProjetoViewUiBinder extends UiBinder<Widget, AddProjetoView>
	{
	}
	
	@UiField
	TextBox nome;
	@UiField
	DateBox dataInicio;
	@UiField
	DateBox dataFim;
	@UiField
	Button btSalvar;
	@UiField
	Hyperlink btCancelar;
	@UiField
	HTMLPanel conteudo;
	
	public AddProjetoView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public TextBox getNome()
	{
		return nome;
	}
	
	@Override
	public HasValue<Date> getDtInicio()
	{
		return dataInicio;
	}
	
	@Override
	public HasValue<Date> getDtFim()
	{
		return dataFim;
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
	
//	@Override
//	public Button getAddRequisitos()
//	{
//		return addRequisitos;
//	}
//	
//	@Override
//	public Button getAddStakeholders()
//	{
//		return addStakeholders;
//	}
	
}
