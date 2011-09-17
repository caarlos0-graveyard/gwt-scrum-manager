package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddUserView extends ViewImpl implements AddUserPresenter.CadastroUsuarioView
{
	
	private Panel spPrincipal;
	private final VerticalPanel vp;
	private final Label labelNome;
	private final TextBox txtNome;
	private final Button btSalvar;
	
	@Inject
	public AddUserView()
	{
		spPrincipal = new DecoratorPanel();
		labelNome = new Label("Nome");
		txtNome = new TextBox();
		btSalvar = new Button("salvar");
		
		vp = new VerticalPanel();
		
		vp.add(labelNome);
		vp.add(txtNome);
		vp.add(btSalvar);
		
		spPrincipal.add(vp);
		RootPanel.get().add(spPrincipal);
	}
	
	@Override
	public Widget asWidget()
	{
		return spPrincipal.asWidget();
	}
	
	@Override
	public HasValue<String> getNome()
	{
		return txtNome;
	}
	
	@Override
	public HasClickHandlers getBtSalvar()
	{
		return btSalvar;
	}
	
}