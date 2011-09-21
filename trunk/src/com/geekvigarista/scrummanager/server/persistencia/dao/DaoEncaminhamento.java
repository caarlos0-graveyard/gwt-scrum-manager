package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.EncaminhamentoPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoEncaminhamento;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Encaminhamento;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

public class DaoEncaminhamento extends BasicDAO<EncaminhamentoPOJO, ObjectId> implements IDaoEncaminhamento
{
	@Inject
	public DaoEncaminhamento()
	{
		super(MongoConnection.getDatastore());
	}
	
	public DaoEncaminhamento(Datastore ds)
	{
		super(ds);
	}
	
	@Override
	public Encaminhamento salvar(Encaminhamento t)
	{
		EncaminhamentoPOJO encPojo = new EncaminhamentoPOJO(t);
		Key<EncaminhamentoPOJO> key = this.save(encPojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		
		encPojo.setId(new ObjectId(key.getId().toString()));
		return encPojo.getEncaminhamento();
	}
	
	@Override
	public boolean excluir(Encaminhamento t)
	{
		try
		{
			this.deleteById(new EncaminhamentoPOJO(t).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Encaminhamento> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	@Override
	public Encaminhamento buscar(String id)
	{
		EncaminhamentoPOJO encPojo = this.findOne("id", new ObjectId(id));
		if(encPojo == null)
		{
			return null;
		}
		return encPojo.getEncaminhamento();
	}
	
	/**
	 * 
	 * Converte um QueryResult para uma lista de Encaminhamento. Caso venha um queryResult vazio, retorna um array vazio de Encaminhamento.
	 * 
	 * @param resultadoBusca
	 *            , QueryResult de EncaminhamentoPojo vindo de um metodo this.find();
	 */
	@Override
	public List<Encaminhamento> toValueObject(QueryResults<EncaminhamentoPOJO> resultadoBusca)
	{
		List<Encaminhamento> retorno = new ArrayList<Encaminhamento>();
		for(EncaminhamentoPOJO encPojo : resultadoBusca.asList())
		{
			retorno.add(encPojo.getEncaminhamento());
		}
		return retorno;
	}
}
