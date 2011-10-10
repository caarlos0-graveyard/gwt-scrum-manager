package com.geekvigarista.scrummanager.client.telas.componentes.loading;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * Resources do Status de loading :D
 * 
 * @author caarlos0
 */
public interface LoadingResources extends ClientBundle
{
	LoadingResources INSTANCE = GWT.create(LoadingResources.class);
	StatusCssStyle CSS = INSTANCE.style();
	
	public interface StatusCssStyle extends CssResource
	{
		public String statusPopupPanelContainer();
		
		public String statusPopupPanel();
		
		public String statusPopupPanelText();
	}
	
	@Source("style.css")
	StatusCssStyle style();
	
	@Source("ajax-loader.gif")
	ImageResource ajaxloader();
}
