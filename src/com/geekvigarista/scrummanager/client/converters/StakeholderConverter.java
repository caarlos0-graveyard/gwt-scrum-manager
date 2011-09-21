package com.geekvigarista.scrummanager.client.converters;

import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.gwt.user.client.ui.HasValue;

public class StakeholderConverter
{
	public static Stakeholder convert(HasValue<String> nome, Usuario usuario, Projeto projeto, PapelStakeholder papel)
	{
		Stakeholder s = new Stakeholder();
		s.setNome(nome.getValue());
		s.setUsuario(usuario);
		s.setProjeto(projeto);
		s.setPapel(papel);
		return s;
	}
}
