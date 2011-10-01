package com.geekvigarista.scrummanager.client.converters;

import java.util.Date;

import com.geekvigarista.scrummanager.client.converters.interfaces.IRequisitoConverter;
import com.geekvigarista.scrummanager.client.telas.cadastros.requisito.AddRequisitoPresenter.AddRequisitoView;
import com.geekvigarista.scrummanager.shared.enums.PrioridadeRequisito;
import com.geekvigarista.scrummanager.shared.vos.Requisito;

public class RequisitoConverter implements IRequisitoConverter
{
	
	@Override
	public Requisito convert(Requisito t, AddRequisitoView v)
	{
		Requisito r = new Requisito();
		if(t != null && t.getId() != null)
		{
			r.setId(t.getId());
			r.setDataCadastro(t.getDataCadastro());
			r.setTempoTotal(t.getTempoTotal());
		}
		else
		{
			r.setDataCadastro(new Date());
			r.setTempoTotal(0);
		}
		
		r.setDescricao(v.descricao().getHTML());
		r.setTempoEstimado(v.tempoEstimado().getValue());
		r.setTitulo(v.titulo().getValue());
		
		PrioridadeRequisito p = PrioridadeRequisito.values()[v.prioridade().getSelectedIndex()];
		r.setPrioridade(p);
		return r;
	}
	
	@Override
	public void updateView(Requisito t, AddRequisitoView v)
	{
		v.titulo().setValue(t.getTitulo());
		v.descricao().setHTML(t.getDescricao());
		v.prioridade().setSelectedIndex(t.getPrioridade().ordinal());
		v.tempoEstimado().setValue(t.getTempoEstimado());
	}
	
}
