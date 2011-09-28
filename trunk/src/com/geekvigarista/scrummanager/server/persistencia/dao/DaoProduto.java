package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.ProdutoPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoProduto;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

public class DaoProduto extends BasicDAO<ProdutoPOJO, ObjectId> implements IDaoProduto
{
	
	@Inject
	public DaoProduto()
	{
		super(MongoConnection.getDatastore());
	}
	
	public DaoProduto(Datastore ds)
	{
		super(ds);
	}
	
	@Override
	public Produto salvar(Produto t)
	{
		ProdutoPOJO prodPojo = new ProdutoPOJO(t);
		Key<ProdutoPOJO> key = this.save(prodPojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		
		prodPojo.setId(new ObjectId(key.getId().toString()));
		return prodPojo.getProduto();
	}
	
	@Override
	public boolean excluir(Produto t)
	{
		try
		{
			this.deleteById(new ProdutoPOJO(t).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Produto> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	@Override
	public Produto buscar(String id)
	{
		ProdutoPOJO prodPojo = this.findOne("id", new ObjectId(id));
		if(prodPojo == null)
		{
			return null;
		}
		return prodPojo.getProduto();
	}
	
	@Override
	public List<Produto> toValueObject(QueryResults<ProdutoPOJO> resultadoBusca)
	{
		List<Produto> retorno = new ArrayList<Produto>();
		for(ProdutoPOJO prodPojo : resultadoBusca.asList())
		{
			retorno.add(prodPojo.getProduto());
		}
		return retorno;	
	}
	
}
