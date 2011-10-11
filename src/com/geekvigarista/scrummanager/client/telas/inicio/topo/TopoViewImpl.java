package com.geekvigarista.scrummanager.client.telas.inicio.topo;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.client.telas.componentes.searchbox.SearchBox;
import com.geekvigarista.scrummanager.client.telas.inicio.topo.TopoPresenter.TopoView;
import com.geekvigarista.scrummanager.client.telas.interfaces.Images;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class TopoViewImpl extends ViewImpl implements TopoView
{
	
	private static TopoViewImplUiBinder uiBinder = GWT.create(TopoViewImplUiBinder.class);
	
	@UiField
	SearchBox txtbusca;
	@UiField
	Image logo;
	
	Widget w;
	
	interface TopoViewImplUiBinder extends UiBinder<Widget, TopoViewImpl>
	{
	}
	
	public TopoViewImpl()
	{
		w = uiBinder.createAndBindUi(this);
		txtbusca.setPlaceholder(Mensagem.get.buscarProjetos());
		logo.setUrl(Images.instance.logo().getSafeUri());
	}
	
	@Override
	public Widget asWidget()
	{
		return w;
	}
	
	@Override
	public SearchBox buscar()
	{
		return txtbusca;
	}
	
}
