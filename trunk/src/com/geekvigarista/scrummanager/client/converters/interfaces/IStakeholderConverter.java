package com.geekvigarista.scrummanager.client.converters.interfaces;

import java.util.List;

import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter;
import com.geekvigarista.scrummanager.client.telas.cadastros.stakeholder.AddStakeholderPresenter.AddStakeholderView;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;

public interface IStakeholderConverter extends ViewBeanConverter<Stakeholder, AddStakeholderPresenter.AddStakeholderView>
{
	
	/**
	 * retorna um bean de stakeholder populado de acordo com a view e com os parametros
	 * 
	 * @param v
	 *            - AddStakeholderView (instancia)
	 * @param usuariosSistema
	 *            - lista de usuarios do combo
	 * @param stakeholderAnterior
	 * @return
	 */
	Stakeholder convert(Stakeholder stakeholderAnterior, AddStakeholderView v, List<Usuario> usuariosSistema);
	
	/**
	 * Popula a view de acordo com o stakeholder recebido por parametro
	 * 
	 * @param stakeholder
	 *            - Stakeholder a ser populado na view
	 * @param v
	 *            getView() da presenter
	 * @param usuariosSistema
	 *            - lista de usuários (que já deve estar carregada na view)
	 */
	void updateView(Stakeholder stakeholder, AddStakeholderView v, List<Usuario> usuariosSistema);
}
