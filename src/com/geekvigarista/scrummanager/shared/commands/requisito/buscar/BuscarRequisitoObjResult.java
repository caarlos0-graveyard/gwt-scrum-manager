package com.geekvigarista.scrummanager.shared.commands.requisito.buscar;

import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.gwtplatform.dispatch.shared.Result;

public class BuscarRequisitoObjResult implements Result
{
	private Requisito response;
	
	public BuscarRequisitoObjResult(Requisito response)
	{
		super();
		this.response = response;
	}
	
	@SuppressWarnings("unused")
	private BuscarRequisitoObjResult()
	{
		// serialization
	}
	
	public Requisito getResponse()
	{
		return response;
	}
	
}
