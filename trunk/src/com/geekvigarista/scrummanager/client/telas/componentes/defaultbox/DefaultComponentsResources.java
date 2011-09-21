package com.geekvigarista.scrummanager.client.telas.componentes.defaultbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface DefaultComponentsResources extends ClientBundle
{
	
	public static DefaultComponentsResources resources = GWT.create(DefaultComponentsResources.class);
	public static DefaultComponentsCss css = resources.css();
	
	public interface DefaultComponentsCss extends CssResource
	{
		String inputWidth();
		
		String listWidth();
	}
	
	@Source("defaultComponents.css")
	DefaultComponentsCss css();
}
