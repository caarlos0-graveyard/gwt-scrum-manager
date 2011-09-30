package com.geekvigarista.scrummanager.client.telas.erros;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class Erro404ViewImpl extends ViewImpl implements Error404Presenter.Erro404View
{
	private static Erro404ViewImplUiBinder uiBinder = GWT.create(Erro404ViewImplUiBinder.class);
	
	interface Erro404ViewImplUiBinder extends UiBinder<Widget, Erro404ViewImpl>
	{
	}
	
	private Widget w;
	
	public Erro404ViewImpl()
	{
		w = uiBinder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
}
