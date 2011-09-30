package com.geekvigarista.scrummanager.client.converters;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.geekvigarista.scrummanager.client.converters.interfaces.IStakeholderConverter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter.AddStakeholderView;
import com.geekvigarista.scrummanager.shared.enums.PapelStakeholder;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

public class StakeholderConverter implements IStakeholderConverter
{
	@Override
	public Stakeholder convert(Stakeholder stakeholderAnterior, AddStakeholderView u, List<Usuario> usuariosSistema)
	{
		Stakeholder stakeholder = new Stakeholder();
		stakeholder.setPapel(PapelStakeholder.values()[u.getPapeis().getSelectedIndex()]);
		stakeholder.setUsuario(usuariosSistema.get(u.getUsuarios().getSelectedIndex()));
		stakeholder.setNome(u.getNome().getValue());
		if(stakeholderAnterior != null && stakeholderAnterior.getId() != null)
		{
			stakeholder.setId(stakeholderAnterior.getId());
		}
		
		return null;
	}
	
	@Override
	public void updateView(Stakeholder stakeholder, AddStakeholderView u, List<Usuario> usuariosSistema)
	{
		if(stakeholder == null)
			return;
		u.getNome().setValue(stakeholder.getNome());
		u.getPapeis().setSelectedIndex(stakeholder.getPapel().ordinal());
		u.getUsuarios().setSelectedIndex(usuariosSistema.indexOf(stakeholder.getUsuario()));
	}
	
	@Override
	@Deprecated
	public Stakeholder convert(Stakeholder t, AddStakeholderView u)
	{
		throw new NotImplementedException();
	}
	
	@Override
	@Deprecated
	public void updateView(Stakeholder t, AddStakeholderView u)
	{
		throw new NotImplementedException();
	}
	
}
