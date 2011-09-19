package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.UsuarioPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.server.persistencia.utils.MongoConnection;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.inject.Inject;

/**
 * Classe Dao que herda de BasicDao do morphia. Implementa metodos de uma interface que herda de IDao
 * 
 * @author Raduq
 */
public class DaoUsuario extends BasicDAO<UsuarioPOJO, ObjectId> implements IDaoUsuario
{
	
	@Inject
	public DaoUsuario()
	{
		super(MongoConnection.getDatastore());
	}
	
	/**
	 * TODO talves usar injeção de dependencia para ter o datastore injetado no dao ?
	 */
	public DaoUsuario(Datastore ds)
	{
		super(ds);
	}
	
	/**
	 * Metodo que salva um Usuario. TODO - melhorar
	 */
	@Override
	public Usuario salvar(Usuario usuario)
	{
		UsuarioPOJO uPojo = new UsuarioPOJO(usuario);
		Key<UsuarioPOJO> key = this.save(uPojo);
		if(key == null)
		{
			return null;
		}
		System.out.println("Salvo com id " + key.toString());
		//FIXME ver isso se funga
		uPojo.setId(new ObjectId(key.getId().toString()));
		return uPojo.getUsuario();
	}
	
	/**
	 * Metodo que exclui um usuario. O usuario deve ter ID preenchido.
	 * 
	 * @param usuario
	 * @return
	 */
	@Override
	public boolean excluir(Usuario usuario)
	{
		try
		{
			//FIXME mudar o jeito de retorno dos metodos
			this.deleteById(new UsuarioPOJO(usuario).getId());
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que busca todos os usuarios sem usar nenhum criterio.
	 */
	@Override
	public List<Usuario> buscarTodos()
	{
		return toValueObject(this.find());
	}
	
	/**
	 * Metodo que busca um usuario de acordo com o id passado por parametro. Se nao encontrar retorna null.
	 */
	@Override
	public Usuario buscar(String id)
	{
		UsuarioPOJO uPojo = this.findOne("id", new ObjectId(id));
		if(uPojo == null)
		{
			return null;
		}
		return uPojo.getUsuario();
	}
	
	/**
	 * Metodo que busca usuarios de acordo com o login deles.
	 * Busca por partes da palavra do login também. (like)
	 */
	@Override
	public List<Usuario> buscarLike(String parametro)
	{
		Query<UsuarioPOJO> query = createQuery().field("login").contains(parametro);
		return toValueObject(this.find(query));
	}
	
	/**
	 * 
	 * Converte um QueryResult para uma lista de Usuario. Caso venha um queryResult vazio, retorna um array vazio de Usuario.
	 * 
	 * @param resultadoBusca
	 *            , QueryResult de UsuarioPojo vindo de um metodo this.find();
	 */
	public List<Usuario> toValueObject(QueryResults<UsuarioPOJO> resultadoBusca)
	{
		List<Usuario> retorno = new ArrayList<Usuario>();
		for(UsuarioPOJO uPojo : resultadoBusca.asList())
		{
			retorno.add(uPojo.getUsuario());
		}
		return retorno;
	}
}
