package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.server.beans.StakeholderPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
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
	public List<Projeto> buscarByUsuario(Usuario usuario)
	{
		//TODO injetar isso ?
		IDaoStakeholder daoStake = new DaoStakeholder();
		List<Stakeholder> stakeholders = daoStake.buscarByUsuario(usuario);
		if(stakeholders == null || stakeholders.isEmpty())
		{
			return null;
		}
		
		Set<Projeto> projetosRetorno = new LinkedHashSet<Projeto>();
		List<StakeholderPOJO> stakesPojo = new ArrayList<StakeholderPOJO>();
		for(Stakeholder s : stakeholders)
		{
			stakesPojo.add(new StakeholderPOJO(s));
		}
		
		for(StakeholderPOJO stakePojo : stakesPojo)
		{
			Query<ProjetoPOJO> query = createQuery().filter("stakeholders.id", stakePojo.getId());
			for(Projeto p : toValueObject(this.find(query)))
			{
				//adiciona projetos vindo dos stakeholders.
				//nao adiciona 2 projetos com o mesmo id.
				
				//FIXME este codigo fico uma bosta, refatorar algum dia...
				if(projetosRetorno.isEmpty())
				{
					projetosRetorno.add(p);
				}else
				{
					boolean podeAdicionar = true;
					for(Projeto projetoAdicionado : projetosRetorno)
					{
						if(projetoAdicionado.getId().equals(p.getId()))
						{
							podeAdicionar = false;
							break;
						}
					}
					if(podeAdicionar)
					{
						projetosRetorno.add(p);
					}
				}
			}
		}
		
		return new ArrayList<Projeto>(projetosRetorno);
		
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
	
	/**
	 * nao implementado....
	 */
	@Override
	@Deprecated
	public List<Projeto> buscarLike(String parametro)
	{
		return null;
	}
	
}
