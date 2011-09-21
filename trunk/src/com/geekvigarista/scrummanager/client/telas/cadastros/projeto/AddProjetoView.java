package com.geekvigarista.scrummanager.client.telas.cadastros.projeto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddProjetoView extends ViewImpl implements AddProjetoPresenter.AddProjetoView
{
	
	private static AddProjetoViewUiBinder uiBinder = GWT.create(AddProjetoViewUiBinder.class);
	
	interface AddProjetoViewUiBinder extends UiBinder<Widget, AddProjetoView>
	{
	}
	
	public AddProjetoView()
	{
		uiBinder.createAndBindUi(this);
	}
	
	public AddProjetoView(String firstName)
	{
		uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return null;
	}
	
}
