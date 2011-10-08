package com.geekvigarista.scrummanager.client.telas.componentes.msgbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ImportedWithPrefix;

public interface MsgBoxResources extends ClientBundle
{
	/**
	 * instancia da interface style
	 */
	static Style css = ResourceInstance.getDefaultResources().css();
	
	/**
	 * classe usada para retornar uma instancia singleton do Style
	 * 
	 * @author caarlos0
	 * 
	 */
	class ResourceInstance
	{
		private static MsgBoxResources defaultResources;
		
		private final static MsgBoxResources getDefaultResources()
		{
			if(defaultResources == null)
			{
				defaultResources = GWT.create(MsgBoxResources.class);
			}
			return defaultResources;
		}
	}
	
	/**
	 * Estilo do MsgBox
	 * 
	 * @return
	 */
	@Source("style.css")
	Style css();
	
	/**
	 * Interface de definição dos styles
	 * 
	 * @author caarlos0
	 * 
	 */
	@ImportedWithPrefix("msgxbox-")
	public interface Style extends CssResource
	{
		/**
		 * estilo da mensagem de sucesso (verde e tal)
		 */
		public String sucesso();
		
		/**
		 * estilo mensagem de erro (vermelha e tal)
		 */
		public String erro();
		
		/**
		 * estilo do popup
		 */
		public String msg();
		
		/**
		 * Estilo emoticon sucesso
		 */
		public String emoticonSucesso();
		
		/**
		 * Estilo emoticon erro
		 * 
		 * @return
		 */
		public String emoticonErro();
	}
}
