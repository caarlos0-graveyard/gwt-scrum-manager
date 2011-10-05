package com.geekvigarista.scrummanager.server.beans;

import org.bson.types.ObjectId;

import com.geekvigarista.scrummanager.shared.vos.Produto;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity("produtos")
public class ProdutoPOJO
{
	@Id
	ObjectId id;
	private String descricao;
	
	public ProdutoPOJO()
	{
	}
	
	public ProdutoPOJO(Produto produto)
	{
		if(produto == null)
		{
			this.id = null;
			this.descricao = null;
		}
		else
		{
			if(produto.getId() != null)
			{
				this.id = new ObjectId(produto.getId());
			}
			this.descricao = produto.getDescricao();
		}
	}
	
	public Produto getProduto()
	{
		if(this.id != null)
		{
			return new Produto(this.id.toString(), this.descricao);
		}
		else
		{
			return new Produto(this.descricao);
		}
	}
	
	public ObjectId getId()
	{
		return id;
	}
	
	public void setId(ObjectId id)
	{
		this.id = id;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
}
