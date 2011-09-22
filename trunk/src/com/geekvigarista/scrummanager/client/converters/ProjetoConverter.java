package com.geekvigarista.scrummanager.client.converters;

import java.util.Date;
import java.util.List;

import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.google.gwt.user.client.ui.HasValue;

public class ProjetoConverter
{
	public static Projeto convert(HasValue<String> nome, HasValue<Date> dataInicio, HasValue<Date> dataFim, List<Requisito> requisitos,
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
}
