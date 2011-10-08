package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.geekvigarista.scrummanager.client.telas.componentes.richtexttoolbar.RichTextToolbar;
import com.google.gwt.event.logical.shared.HasInitializeHandlers;
import com.google.gwt.event.logical.shared.InitializeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.HasSafeHtml;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * Componente RichTextArea com a toolbar.
 * 
 * @author caarlos0
 * 
 */
public class DefaultRichTextArea extends Composite implements HasHTML, HasInitializeHandlers, HasSafeHtml
{
	private final RichTextArea area;
	private final RichTextToolbar toolbar;
	private final Grid grid;
	
	public DefaultRichTextArea()
	{
		area = new RichTextArea();
		area.ensureDebugId("cwRichText-area");
		area.setSize("100%", "14em");
		toolbar = new RichTextToolbar(area);
		toolbar.ensureDebugId("cwRichText-toolbar");
		toolbar.setWidth("100%");
		
		grid = new Grid(2, 1);
		grid.setStyleName("cw-RichText");
		grid.setWidget(0, 0, toolbar);
		grid.setWidget(1, 0, area);
		initWidget(grid);
	}
	
	@Override
	public Widget asWidget()
	{
		return grid;
	}
	
	@Override
	public String getText()
	{
		return area.getText();
	}
	
	@Override
	public void setText(String text)
	{
		area.setText(text);
	}
	
	@Override
	public void setHTML(SafeHtml html)
	{
		area.setHTML(html);
	}
	
	@Override
	public HandlerRegistration addInitializeHandler(InitializeHandler handler)
	{
		return area.addInitializeHandler(handler);
	}
	
	@Override
	public String getHTML()
	{
		return area.getHTML();
	}
	
	@Override
	public void setHTML(String html)
	{
		area.setHTML(html);
	}
	
}
