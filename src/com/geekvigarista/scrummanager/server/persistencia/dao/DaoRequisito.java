package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.server.beans.RequisitoPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoRequisito;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Projeto;
import com.geekvigarista.scrummanager.shared.vos.Requisito;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

public class DaoRequisito extends BasicDAO<RequisitoPOJO, ObjectId> implements IDaoRequisito
{
	@Inject
	public DaoRequisito()
	{
		super(MongoConnection.getDatastore());
	}
	
	public DaoRequisito(Datastore ds)
	{
		super(ds);
	}
	
	@Override
	public Requisito salvar(Requisito t)
	{
		RequisitoPOJO reqPojo = new RequisitoPOJO(t);
		Key<RequisitoPOJO> key = this.save(reqPojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		
		reqPojo.setId(new ObjectId(key.getId().toString()));
		return reqPojo.getRequisito();
	}
	
	@Override
	public boolean excluir(Requisito t)
	{
		try
		{
			this.deleteById(new RequisitoPOJO(t).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Requisito> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	@Override
	public Requisito buscar(String id)
	{
		RequisitoPOJO reqPojo = this.findOne("id", new ObjectId(id));
		if(reqPojo == null)
		{
			return null;
		}
		return reqPojo.getRequisito();
	}
	
	@Override
	public List<Requisito> buscarByProjeto(Projeto projeto)
	{
		ProjetoPOJO projetoPojo = new ProjetoPOJO(projeto);
		Query<RequisitoPOJO> query = createQuery().filter("projeto", projetoPojo);
		return toValueObject(this.find(query));
	}
	
	/**
	 * 
	 * Converte um QueryResult para uma lista de Requisito. Caso venha um queryResult vazio, retorna um array vazio de Requisito.
	 * 
	 * @param resultadoBusca
	 *            , QueryResult de RequisitoPojo vindo de um metodo this.find();
	 */
	@Override
	public List<Requisito> toValueObject(QueryResults<RequisitoPOJO> resultadoBusca)
	{
		List<Requisito> retorno = new ArrayList<Requisito>();
		for(RequisitoPOJO reqPojo : resultadoBusca.asList())
		{
			retorno.add(reqPojo.getRequisito());
		}
		return retorno;
	}
}
