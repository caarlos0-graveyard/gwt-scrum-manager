package com.geekvigarista.scrummanager.client.converters.interfaces;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter.AddProjetoView;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.geekvigarista.scrummanager.shared.vos.Projeto;

public interface IProjetoConverter extends ViewBeanConverter<Projeto, AddProjetoPresenter.AddProjetoView>
{
	public Projeto convert(Projeto t, AddProjetoPresenter.AddProjetoView v, List<Produto> produtos);
	
	void updateView(Projeto t, AddProjetoView u, List<Produto> produtos);
}
