package com.geekvigarista.scrummanager.server.interfaces.dao;

import java.util.List;

import com.google.code.morphia.query.QueryResults;

/**
 * Interface para os Daos. Aqui ficam descritos todos os metodos comuns para todos os Daos
 * 
 * @author Raduq
 * @param T
 *            - Value Object simples, usado no client. K - Pojo com anotacoes do morphia, usado somente para persistencia.
 */
public interface IDao<T, K>
{
	/**
	 * Salva o objeto e retorna o objeto salvo ja com id.
	 * 
	 * @param t
	 *            , objeto a ser salvo.
	 */
	T salvar(T t);
	
	/**
	 * Exclui o objeto e retorna um resultado. Obrigatoriamente o objeto deve ter id.
	 * 
	 * @param t
	 */
	boolean excluir(T t);
	
	/**
	 * Busca uma lista com todos os objetos do banco sem nenhum limite ou criterio.
	 * 
	 * @return lista de <T> com todos os objetos
	 */
	List<T> buscarTodos();
	
	/**
	 * Busca um objeto <T> de acordo com seu id.
	 * 
	 * @param id
	 *            , id do objeto a ser buscado.
	 */
	T buscar(String id);
	
	/**
	 * Converte uma lista de resultados de um metodo find() para uma lista de valueObjects.
	 */
	List<T> toValueObject(QueryResults<K> resultadoBusca);
}
