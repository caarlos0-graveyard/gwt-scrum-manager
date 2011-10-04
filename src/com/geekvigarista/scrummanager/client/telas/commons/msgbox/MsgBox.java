package com.geekvigarista.scrummanager.client.telas.commons.msgbox;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MsgBox extends Composite
{
	
	private static MsgBoxUiBinder uiBinder = GWT.create(MsgBoxUiBinder.class);
	
	@UiField
	PopupPanel popup;
	
	@UiField
	HorizontalPanel horizontalPanel;
	
	MsgBoxResources resources = GWT.create(MsgBoxResources.class);
	{
		resources.css().ensureInjected(); // garante que o css será injetado na página
	}
	
	interface MsgBoxUiBinder extends UiBinder<Widget, MsgBox>
	{
	}
	
	public MsgBox(String mensagem, boolean error)
	{
		List<String> mensagens = new ArrayList<String>();
		mensagens.add(mensagem);
		buildMsgBox(mensagens, error);
	}
	
	public MsgBox(List<String> mensagens, boolean error)
	{
		buildMsgBox(mensagens, error);
	}
	
	private void buildMsgBox(List<String> mensagens, boolean error)
	{
		// bind inicial
		initWidget(uiBinder.createAndBindUi(this));
		
		// vertical panel
		VerticalPanel vpmsgs = new VerticalPanel();
		Label l;
		
		// itera as msgs adicionando os labels...
		for(String m : mensagens)
		{
			l = new Label(m);
			l.addStyleName(resources.css().msg());
			l.addStyleName(error ? resources.css().erro() : resources.css().sucesso());
			vpmsgs.add(l);
			l.setWidth("490px");
		}
		
		// add elas
		horizontalPanel.add(vpmsgs);
		
		// cria o label do emoticon :)
		l = new Label(error ? ":(" : ":)");
		l.addStyleName(error ? resources.css().emoticonErro() : resources.css().emoticonSucesso());
		horizontalPanel.add(l);
		
		// centraliza o popup
		popup.center();
		
		// fecha o popup automagicamente depois de 1min
		Timer t = new Timer()
		{
			@Override
			public void run()
			{
				popup.hide();
			}
		};
		t.schedule(60000);
	}
	
}
