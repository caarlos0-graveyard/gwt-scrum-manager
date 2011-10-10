package com.geekvigarista.scrummanager.client.telas.componentes.loading;

import com.geekvigarista.scrummanager.client.i18n.Mensagem;
import com.geekvigarista.scrummanager.client.telas.componentes.loading.LoadingResources.StatusCssStyle;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;

/**
 * Popup de status (carregando)
 * 
 * @see StatusPopupPanelHandler
 * 
 * @author caarlos0
 */
public class StatusPopupPanel extends DialogBox
{
	private static StatusCssStyle css = LoadingResources.CSS;
	
	private final Grid grid = new Grid(1, 4);
	
	public StatusPopupPanel()
	{
		super(false);
		build();
	}
	
	private void build()
	{
		setStylePrimaryName(css.statusPopupPanelContainer());
		grid.getRowFormatter().setStyleName(0, css.statusPopupPanelText());
		grid.setStyleName(css.statusPopupPanel());
		setWidget(grid);
		
		/*
		 * essa gambia abaixo foi necessária porque o grid não respeitava meus margins.
		 */
		grid.setText(0, 0, " ");
		grid.setText(0, 1, " ");
		grid.setText(0, 2, " ");
		grid.setText(0, 3, Mensagem.get.carregando());
	}
	
	@Override
	public void show()
	{
		StyleInjector.inject(css.getText(), true);
		super.show();
	}
	
}
