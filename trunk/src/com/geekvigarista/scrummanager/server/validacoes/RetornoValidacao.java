package com.geekvigarista.scrummanager.server.validacoes;

import java.util.List;

public class RetornoValidacao
{
	private boolean ok;
	private List<String> erros;
	
	public RetornoValidacao(){}
	
	public RetornoValidacao(boolean ok)
	{
		this.ok = ok;
	}
	
	public RetornoValidacao(boolean ok,List<String> erros)
	{
		this.ok = ok;
		this.erros = erros;
	}

	/**
	 * @return the ok
	 */
	public boolean isOk()
	{
		return ok;
	}

	/**
	 * @param ok the ok to set
	 */
	public void setOk(boolean ok)
	{
		this.ok = ok;
	}

	/**
	 * @return the erros
	 */
	public List<String> getErros()
	{
		return erros;
	}

	/**
	 * @param erros the erros to set
	 */
	public void setErros(List<String> erros)
	{
		this.erros = erros;
	}
	
	
}
