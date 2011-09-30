package com.geekvigarista.scrummanager.client.converters;

import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter.AddProjetoView;
import com.geekvigarista.scrummanager.shared.vos.Projeto;


public interface IProjetoConverter extends ViewBeanConverter<Projeto, AddProjetoPresenter.AddProjetoView>
{
	public Projeto convert(AddProjetoView v, Projeto projeto);
}
