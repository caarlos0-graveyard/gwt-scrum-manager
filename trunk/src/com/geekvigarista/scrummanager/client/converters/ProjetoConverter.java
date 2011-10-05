package com.geekvigarista.scrummanager.client.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.client.converters.interfaces.IProjetoConverter;
import com.geekvigarista.scrummanager.client.telas.cadastros.projeto.AddProjetoPresenter.AddProjetoView;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.user.client.ui.HasValue;

public class ProjetoConverter implements IProjetoConverter
{
	private Projeto convert(HasValue<String> nome, HasValue<Date> dataInicio, HasValue<Date> dataFim, List<Requisito> requisitos,
			List<Stakeholder> stakeholders)
	{
		Projeto p = new Projeto();
		p.setNome(nome.getValue());
		p.setDataInicio(dataInicio.getValue());
		p.setDataFim(dataFim.getValue());
		p.setRequisitos(requisitos);
		p.setStakeholders(stakeholders);
		return p;
	}
	
	@Override
	@Deprecated
	public Projeto convert(Projeto projeto, AddProjetoView v)
	{
		boolean projetoNull = projeto == null || projeto.getId() == null;
		List<Requisito> requisitos = projetoNull ? new ArrayList<Requisito>() : projeto.getRequisitos();
		List<Stakeholder> stakeholders = projetoNull ? new ArrayList<Stakeholder>() : projeto.getStakeholders();
		Projeto p = convert(v.getNome(), v.getDtInicio(), v.getDtFim(), requisitos, stakeholders);
		if(!projetoNull)
		{
			p.setId(projeto.getId());
		}
		return p;
	}
	
	@Override
	public void updateView(Projeto t, AddProjetoView u)
	{
		if(t != null)
		{
			u.getDtFim().setValue(t.getDataFim());
			u.getDtInicio().setValue(t.getDataInicio());
			u.getNome().setValue(t.getNome());
		}
	}
	
	@Override
	public Projeto convert(Projeto t, AddProjetoView v, List<Produto> produtos)
	{
		Projeto p = convert(t, v);
		p.setProduto(produtos.get(v.getLBProdutos().getSelectedIndex()));
		return p;
	}
	
	@Override
	public void updateView(Projeto t, AddProjetoView u, List<Produto> produtos)
	{
		updateView(t, u);
		u.getLBProdutos().setSelectedIndex(produtos.indexOf(t.getProduto()));
	}
	
}
