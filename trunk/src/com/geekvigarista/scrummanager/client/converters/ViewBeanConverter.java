package com.geekvigarista.scrummanager.client.converters;

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
	public T convert(U u);
}
