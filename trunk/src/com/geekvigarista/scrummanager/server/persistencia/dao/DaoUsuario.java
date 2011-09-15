package com.geekvigarista.scrummanager.server.persistencia.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.server.beans.UsuarioPOJO;
import com.geekvigarista.scrummanager.server.interfaces.dao.IDaoUsuario;
import com.geekvigarista.scrummanager.shared.vos.Usuario;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;

/**
 * Classe Dao que herda de BasicDao do morphia.
 * Implementa metodos de uma interface que herda de IDao 
 * @author Raduq
 */
public class DaoUsuario extends BasicDAO<UsuarioPOJO, ObjectId> implements IDaoUsuario 
{
	//TODO talves usar injeção de dependencia para 
	//ter o datastore injetado no dao ?
	//ver isso com o carlos
	protected DaoUsuario(Datastore ds) {
		super(ds);
	}
	
	/**
	 * Metodo que salva um Usuario.
	 * TODO - melhorar
	 */
	@Override
	public Usuario salvar(Usuario usuario) 
	{
		UsuarioPOJO uPojo = new UsuarioPOJO(usuario);
		Key<UsuarioPOJO> key = this.save(uPojo);
		if(key==null){
			return null;
		}
	
		//FIXME ver isso se funga
		uPojo.setId(new ObjectId(key.toString()));
		return uPojo.getUsuario();
	}
	
	/**
	 * Metodo que exclui um usuario.
	 * O usuario deve ter ID preenchido.
	 * 
	 * XXX implementar este metodo.
	 * @param usuario
	 * @return
	 */
	@Override
	public boolean excluir(Usuario usuario) 
	{
		return false;
	}

	/**
	 * Metodo que busca todos os usuarios sem 
	 * usar nenhum criterio.
	 * XXX implementar este metodo.
	 */
	@Override
	public List<Usuario> buscarTodos() {
		return null;
	}
	
	/**
	 * Metodo que busca um usuario de acordo com o id
	 * passado por parametro.
	 * XXX implementar este metodo.
	 */
	@Override
	public Usuario buscar(String id) {
		return null;
	}

}
