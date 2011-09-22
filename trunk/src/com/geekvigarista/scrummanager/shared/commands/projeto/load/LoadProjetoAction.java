package com.geekvigarista.scrummanager.shared.commands.projeto.load;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class LoadProjetoAction extends UnsecuredActionImpl<LoadProjetoResult>
{
	private String id;
	
	public LoadProjetoAction(String id)
	{
		super();
		this.id = id;
	}
	
	@SuppressWarnings("unused")
	private LoadProjetoAction()
	{
	}
	
	public String getId()
	{
		return id;
	}
	
}
