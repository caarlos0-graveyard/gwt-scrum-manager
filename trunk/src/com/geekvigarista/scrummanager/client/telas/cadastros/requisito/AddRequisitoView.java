package com.geekvigarista.scrummanager.client.telas.cadastros.requisito;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.PopupViewImpl;

public class AddRequisitoView extends PopupViewImpl implements AddRequisitoPresenter.AddRequisitoView
{
	
	@Inject
	public AddRequisitoView(EventBus eventBus)
	{
		super(eventBus);
		uiBinder.createAndBindUi(this);
	}
	
	private static AddRequisitoViewUiBinder uiBinder = GWT.create(AddRequisitoViewUiBinder.class);
	
	interface AddRequisitoViewUiBinder extends UiBinder<Widget, AddRequisitoView>
	{
	}
	
	//	private Widget w;
	
	@UiField
	DialogBox conteudo;
	
	@Override
	public Widget asWidget()
	{
		return conteudo;
	}
	
	@Override
	public DialogBox getDialog()
	{
		return conteudo;
	}
	
}
