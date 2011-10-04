package com.geekvigarista.scrummanager.client.telas.cadastros.stakproj;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddStakToProjViewImpl extends Composite
{
	
	private static AddStakToProjViewImplUiBinder uiBinder = GWT.create(AddStakToProjViewImplUiBinder.class);
	
	interface AddStakToProjViewImplUiBinder extends UiBinder<Widget, AddStakToProjViewImpl>
	{
	}
	
	public AddStakToProjViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}
