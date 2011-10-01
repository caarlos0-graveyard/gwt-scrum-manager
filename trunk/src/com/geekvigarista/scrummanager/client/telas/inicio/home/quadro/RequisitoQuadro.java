package com.geekvigarista.scrummanager.client.telas.inicio.home.quadro;

import com.geekvigarista.scrummanager.client.place.NameTokens;
import com.geekvigarista.scrummanager.client.place.Parameters;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequisitoQuadro extends Composite
{
	
	private static RequisitoQuadroUiBinder uiBinder = GWT.create(RequisitoQuadroUiBinder.class);
	@UiField
	Label titulo;
	
	@UiField
	Hyperlink detalhes;
	
	@UiField
	Button next;
	
	@UiField
	Button previous;
	
	interface RequisitoQuadroUiBinder extends UiBinder<Widget, RequisitoQuadro>
	{
	}
	
	public RequisitoQuadro(Requisito requisito)
	{
		initWidget(uiBinder.createAndBindUi(this));
		titulo.setText(requisito.getTitulo());
		
		StringBuilder sb = new StringBuilder();
		sb.append(NameTokens.visreq);
		sb.append(";").append(Parameters.reqid).append("=").append(requisito.getId());
		detalhes.setTargetHistoryToken(sb.toString());
		
		/*
		 * TODO encaminhar
		 * 
		 * ao clicar no botão, abrir um modalzinho, com a descricao, usuario destino e tempo gasto e um botao salvar.
		 * 
		 * ao salvar, executa a acao, e atualiza o quadro (mais seguro quanto a html bugado).
		 * 
		 */
	}
	
}
