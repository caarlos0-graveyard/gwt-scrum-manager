package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProjeto;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
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
	 *	nao implementado....
	*/
	@Override
	@Deprecated
	public List<Projeto> buscarLike(String parametro)
	{
		return null;
	}
	
}
