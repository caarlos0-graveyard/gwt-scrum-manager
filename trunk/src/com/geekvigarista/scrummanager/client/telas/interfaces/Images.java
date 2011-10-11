package com.geekvigarista.scrummanager.client.telas.interfaces;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Images extends ClientBundle
{
	
	Images instance = GWT.create(Images.class);
	
	@Source("logo.png")
	ImageResource logo();
}
