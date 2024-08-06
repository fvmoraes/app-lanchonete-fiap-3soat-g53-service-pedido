package com.fiap.lanchonete.infrastructure.mapper;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.ProdutoResponse;
import com.fiap.lanchonete.infrastructure.requestsdto.ProdutoRequest;

public class ProdutoRequestMapper {
	
	public Produto paraObjetoDominio(ProdutoRequest produtoRequest) {
		return new Produto(Categoria.valueOf(produtoRequest.getCategoria().toString()), produtoRequest.getNome(), produtoRequest.getDescricao(), produtoRequest.getValor());

	}
  
	public String paraResponse(Produto Produto) {
		return new ProdutoResponse(Produto.getCategoria(), Produto.getNome(), Produto.getDescricao(), Produto.getValor()).toString();
	}
	
}
