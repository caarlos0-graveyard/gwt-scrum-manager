package com.geekvigarista.scrummanager.client.telas.componentes.searchbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * Interface do campo de busca.
 * 
 * @author caarlos0
 */
public interface Resources extends ClientBundle
{
	
	Resources INSTANCE = GWT.create(Resources.class);
	SearchCss CSS = INSTANCE.style();
	
	/**
	 * interface com os estilos do css.
	 * 
	 * @author caarlos0
	 */
	public interface SearchCss extends CssResource
	{
		String box();
		
		String focus();
		
		String blur();
		
		String button();
	}
	
	@Source("style.css")
	SearchCss style();
	
	@Source("lupe.png")
	ImageResource lupe();
}