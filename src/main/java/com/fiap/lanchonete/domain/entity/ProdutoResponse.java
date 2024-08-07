package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;

public class ProdutoResponse{
	Categoria categoria;
	String nome;
	String descricao;
	BigDecimal valor;
	public ProdutoResponse(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		super();
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}
	public Categoria getCategoria() {
		return categoria;
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "ProdutoResponse [categoria=" + categoria + ", nome=" + nome + ", descricao=" + descricao + ", valor="
				+ valor + "]";
	}
	public String getDescricao() {
		return descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
		
}