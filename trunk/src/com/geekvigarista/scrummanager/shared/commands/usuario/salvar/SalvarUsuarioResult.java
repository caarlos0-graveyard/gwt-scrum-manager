package com.geekvigarista.scrummanager.shared.commands.usuario.salvar;

import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.gwtplatform.dispatch.shared.Result;

public class SalvarUsuarioResult implements Result {
	private Usuario response;
	private List<String> erros;

	public SalvarUsuarioResult(Usuario response) {
		super();
		this.response = response;
	}
	
	public SalvarUsuarioResult(List<String> erros) {
		super();
		this.erros = erros;
	}
	
	public SalvarUsuarioResult(Usuario response,List<String> erros) {
		super();
		this.response = response;
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	private SalvarUsuarioResult() {
		// serialization
	}

	public Usuario getResponse() {
		return response;
	}
	
	public List<String> getErros()
	{
		return erros;
	}

}
