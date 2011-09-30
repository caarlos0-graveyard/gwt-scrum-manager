package com.geekvigarista.scrummanager.client.converters.interfaces;

import java.io.Serializable;

import com.gwtplatform.mvp.client.View;

/**
 * Interface genérica para conversão de telas em beans.
 * @author caarlos0
 *
 * @param <T> Bean serializável a ser retornado.
 * @param <U> Interface da view de onde devem vir os valores, extendendo {@link View}.
 */
public interface ViewBeanConverter<T extends Serializable, U extends View>
{
	/**
	 * Converte os valores da view para um bean.
	 * @param v getView() da presenter
	 * @param t versao em cache do objeto, para possivel copia de id caso seja uma edicao
	 * @return Objeto populado
	 */
	public T convert(T t, U v);
	
	/**
	 * Popula a view com os valores do bean
	 * @param t Objeto (bean) com populado
	 * @param v getView() da presenter
	 */
	public void updateView(T t, U v);
}
