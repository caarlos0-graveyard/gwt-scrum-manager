package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.StakeholderPOJO;
import com.geekvigarista.scrummanager.server.beans.UsuarioPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoStakeholder;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Stakeholder;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

public class DaoStakeholder extends BasicDAO<StakeholderPOJO, ObjectId> implements IDaoStakeholder
{
	@Inject
	public DaoStakeholder()
	{
		super(MongoConnection.getDatastore());
	}
	
	public DaoStakeholder(Datastore ds)
	{
		super(ds);
	}
	
	@Override
	public Stakeholder salvar(Stakeholder t)
	{
		StakeholderPOJO stakePojo = new StakeholderPOJO(t);
		Key<StakeholderPOJO> key = this.save(stakePojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		
		stakePojo.setId(new ObjectId(key.getId().toString()));
		return stakePojo.getStakeholder();
	}
	
	@Override
	public boolean excluir(Stakeholder t)
	{
		try
		{
			this.deleteById(new StakeholderPOJO(t).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Stakeholder> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	/**
	 * Metodo que busca stakeholder de acordo com o nome deles.
	 * Busca por partes da palavra do login também. (like)
	 */
	@Override
	public List<Stakeholder> buscarLike(String parametro)
	{
		Query<StakeholderPOJO> query = createQuery().field("nome").contains(parametro);
		return toValueObject(this.find(query));
	}
	
	@Override
	public Stakeholder buscar(String id)
	{
		StakeholderPOJO stakePojo = this.findOne("id", new ObjectId(id));
		if(stakePojo == null)
		{
			return null;
		}
		return stakePojo.getStakeholder();
	}
	
	/**
	 * Retorna todos os stakeholders desse usuario.
	 */
	@Override
	public List<Stakeholder> buscarByUsuario(Usuario usuario)
	{
		UsuarioPOJO usuarioPojo = new UsuarioPOJO(usuario);
		Query<StakeholderPOJO> query = createQuery().filter("usuario", usuarioPojo);
		return toValueObject(this.find(query));
	}
	
	/**
	 * 
	 * Converte um QueryResult para uma lista de Stakeholder. Caso venha um queryResult vazio, retorna um array vazio de Stakeholder.
	 * 
	 * @param resultadoBusca
	 *            , QueryResult de StakeholderPojo vindo de um metodo this.find();
	 */
	@Override
	public List<Stakeholder> toValueObject(QueryResults<StakeholderPOJO> resultadoBusca)
	{
		List<Stakeholder> retorno = new ArrayList<Stakeholder>();
		for(StakeholderPOJO stakePojo : resultadoBusca.asList())
		{
			retorno.add(stakePojo.getStakeholder());
		}
		return retorno;
	}
}
