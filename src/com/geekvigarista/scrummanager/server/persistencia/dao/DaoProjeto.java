package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.server.beans.StakeholderPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.dtos.ProjetoStakeholderDTO;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

public class DaoProjeto extends BasicDAO<ProjetoPOJO, ObjectId> implements IDaoProjeto
{
	@Inject
	public DaoProjeto()
	{
		super(MongoConnection.getDatastore());
	}
	
	public DaoProjeto(Datastore ds)
	{
		super(ds);
	}
	
	@Override
	public Projeto salvar(Projeto t)
	{
		ProjetoPOJO projPojo = new ProjetoPOJO(t);
		Key<ProjetoPOJO> key = this.save(projPojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		
		projPojo.setId(new ObjectId(key.getId().toString()));
		return projPojo.getProjeto();
	}
	
	@Override
	public boolean excluir(Projeto t)
	{
		try
		{
			this.deleteById(new ProjetoPOJO(t).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Projeto> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	@Override
	public Projeto buscar(String id)
	{
		ProjetoPOJO projPojo = this.findOne("id", new ObjectId(id));
		if(projPojo == null)
		{
			return null;
		}
		return projPojo.getProjeto();
	}
	
	/**
	 * Busca por todos os projetos que tenham o usuario parametro
	 * atuando como stakeholder.
	 */
	public List<ProjetoStakeholderDTO> buscarByUsuario(Usuario usuario)
	{
		IDaoStakeholder daoStake = new DaoStakeholder();
		List<Stakeholder> stakeholders = daoStake.buscarByUsuario(usuario);
		if(stakeholders == null || stakeholders.isEmpty())
		{
			return null;
		}
		
		List<ProjetoStakeholderDTO> projetosRetorno = new ArrayList<ProjetoStakeholderDTO>();
		List<StakeholderPOJO> stakesPojo = new ArrayList<StakeholderPOJO>();
		for(Stakeholder s : stakeholders)
		{
			stakesPojo.add(new StakeholderPOJO(s));
		}
		
		for(StakeholderPOJO stakePojo : stakesPojo)
		{
			Query<ProjetoPOJO> query = createQuery().filter("stakeholders", stakePojo);
			for(Projeto p : toValueObject(this.find(query)))
			{
				//adiciona projetos vindo dos stakeholders.
				//nao adiciona 2 projetos com o mesmo id.
				
				//FIXME este codigo fico uma bosta, refatorar algum dia... boa sorte então.
				if(projetosRetorno.isEmpty())
				{
					projetosRetorno.add(new ProjetoStakeholderDTO(p, stakePojo.getStakeholder()));
				}
				else
				{
					boolean podeAdicionar = true;
					for(ProjetoStakeholderDTO projetoAdicionado : projetosRetorno)
					{
						if(projetoAdicionado.getProjeto().getId().equals(p.getId()))
						{
							podeAdicionar = false;
							break;
						}
					}
					if(podeAdicionar)
					{
						projetosRetorno.add(new ProjetoStakeholderDTO(p, stakePojo.getStakeholder()));
					}
				}
			}
		}
		
		return projetosRetorno;
		
	}
	
	@Override
	public Projeto carregarRequisitos(Projeto projeto)
	{
		if(projeto == null || projeto.getId() == null)
		{
			return null;
		}
		
		IDaoRequisito daoR = new DaoRequisito();
		Projeto retorno = new Projeto();
		
		List<Requisito> reqs = daoR.buscarByProjeto(projeto);
		retorno.setRequisitos(reqs);
		return retorno;
	}
	
	@Override
	public List<Projeto> toValueObject(QueryResults<ProjetoPOJO> resultadoBusca)
	{
		List<Projeto> retorno = new ArrayList<Projeto>();
		for(ProjetoPOJO projPojo : resultadoBusca.asList())
		{
			retorno.add(projPojo.getProjeto());
		}
		return retorno;
	}
	
	@Override
	public List<Projeto> buscarLike(String parametro, Usuario usuario)
	{
		if(usuario == null)
		{
			return new ArrayList<Projeto>();
		}
		if(parametro == null || parametro.isEmpty())
		{
			if(usuario.isAdministrador())
			{
				return buscarTodos();
			}
			else
			{
				return convertProjetoStakeholderDTO(buscarByUsuario(usuario));
			}
		}
		
		IDaoStakeholder daoS = new DaoStakeholder();
		Query<ProjetoPOJO> query;
		
		//XXX MANO, fuja longe deste codigo....
		//tem que substituir por um field("stakeholders").hasAnyOf(...),
		//mas nao sei porque nao ta fungando :S
		if(!usuario.isAdministrador())
		{
			List<Stakeholder> stakes = daoS.buscarByUsuario(usuario);
			List<Projeto> retorno = new ArrayList<Projeto>();
			query = createQuery().field("nome").containsIgnoreCase(parametro);
			for(ProjetoPOJO p : this.find(query))
			{
				for(StakeholderPOJO st : p.getStakeholders())
				{	
					for(Stakeholder stk : stakes)
					{
						if(st.getId().equals(stk.getId()))
						{
							Projeto proj = p.getProjeto();
							if(!retorno.contains(proj)){
								retorno.add(proj);
							}
						}
					}
				}
			}
			return retorno;
		}
		else
		{
			query = createQuery().field("nome").containsIgnoreCase(parametro);
			return toValueObject(this.find(query));
		}
		
	}
	
	/**
	 * Converter um List de ProjetoStakeholderDTO para um list de Projeto.
	 */
	private List<Projeto> convertProjetoStakeholderDTO(List<ProjetoStakeholderDTO> projetosDTO)
	{
		List<Projeto> retorno = new ArrayList<Projeto>();
		
		for(ProjetoStakeholderDTO p : projetosDTO)
		{
			retorno.add(p.getProjeto());
		}
		
		return retorno;
	}
}
