package com.geekvigarista.scrummanager.server.persistencia.utils;

import com.geekvigarista.scrummanager.server.beans.EncaminhamentoPOJO;
import com.geekvigarista.scrummanager.server.beans.ProjetoPOJO;
import com.geekvigarista.scrummanager.server.beans.RequisitoPOJO;
import com.geekvigarista.scrummanager.server.beans.StakeholderPOJO;
import com.geekvigarista.scrummanager.server.beans.UsuarioPOJO;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

/**
 * Provem uma conexao com o mongoDB utilizando o framework Morphia.
 * 
 * @author Raduq
 * @version 1.0
 */
public class MongoConnection 
{
	private static final String BANCO = "scrumDB";
	private static final String HOST = "localhost";
	private static final int PORT = 27017;

	private static Mongo mongo;
	private static Morphia morphia;
	private static Datastore datastore;

	/**
	 * Retorna uma instancia de Mongo. Caso nao tenha nenhuma, cria uma.
	 */
	private static Mongo getMongo() 
	{
		if (mongo == null) 
		{
			try 
			{
				mongo = new Mongo(HOST, PORT);
			} catch (Exception e) 
			{
				//TODO melhorar isto
				e.printStackTrace();
			}
		}
		return mongo;
	}
	
	/**
	 * Retorna uma instancia do Morphia.
	 * Caso nao tenha nenhuma cria uma, e mapea as classes.
	 * Tambem da ensureIndex nos indices do mongo.
	 */
	private static Morphia getMorphia() 
	{
		if (morphia == null) 
		{
			morphia = new Morphia();
			morphia.map(UsuarioPOJO.class)
				   .map(StakeholderPOJO.class)
				   .map(ProjetoPOJO.class)
			       .map(EncaminhamentoPOJO.class)
			       .map(RequisitoPOJO.class);
			// TODO mapear os outros beans
			// TODO inserir ensureIndex
		}
		return morphia;
	}
	
	/**
	 * Retorna o datastore.
	 * Caso nao tenha nenhum cria um.
	 */
	public static Datastore getDatastore() 
	{
		if (datastore == null) 
		{
			datastore = getMorphia().createDatastore(getMongo(), BANCO);
			datastore.ensureIndexes();
		}
		return datastore;
	}

}
