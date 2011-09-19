package com.geekvigarista.scrummanager.client.telas.cadastros.usuario;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class AddUserView extends ViewImpl implements AddUserPresenter.CadastroUsuarioView
{
	
	private Panel spPrincipal;
	private final FlexTable tabela;
	private final Label labelNome;
	private final Label labelLogin;
	private final Label labelSenha;
	private final Label labelConfSenha;
	private final TextBox txtNome;
	private final TextBox txtLogin;
	private final PasswordTextBox txtSenha;
	private final PasswordTextBox txtConfSenha;
	private final Button btSalvar;
	private final Hyperlink btCancelar;
	
	@Inject
	public AddUserView()
	{
		spPrincipal = new DecoratorPanel();
		
		labelNome = new Label(Mensagem.get.nome());
		txtNome = new TextBox();
		
		labelLogin = new Label(Mensagem.get.login());
		txtLogin = new TextBox();
		
		labelSenha = new Label(Mensagem.get.senha());
		txtSenha = new PasswordTextBox();
		
		labelConfSenha = new Label(Mensagem.get.confirmacaSenha());
		txtConfSenha = new PasswordTextBox();
		
		btSalvar = new Button(Mensagem.get.salvar());
		btCancelar = new Hyperlink();
		btCancelar.setText(Mensagem.get.cancelar());
		
		tabela = new FlexTable();
		
		tabela.setWidget(0, 0, labelNome);
		tabela.setWidget(0, 1, txtNome);
		tabela.setWidget(1, 0, labelLogin);
		tabela.setWidget(1, 1, txtLogin);
		tabela.setWidget(2, 0, labelSenha);
		tabela.setWidget(2, 1, txtSenha);
		tabela.setWidget(3, 0, labelConfSenha);
		tabela.setWidget(3, 1, txtConfSenha);
		
		tabela.setWidget(4, 0, btSalvar);
		tabela.setWidget(4, 1, btCancelar);
		
		spPrincipal.add(tabela);
//		RootPanel.get().add(spPrincipal);
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
	
	@Override
	public HasValue<String> getLogin()
	{
		return txtLogin;
	}
	
	@Override
	public HasValue<String> getSenha()
	{
		return txtSenha;
	}
	
	@Override
	public HasValue<String> getConfSenha()
	{
		return txtConfSenha;
	}
	
	@Override
	public HasClickHandlers getBtCancelar()
	{
		return btCancelar;
	}
}
