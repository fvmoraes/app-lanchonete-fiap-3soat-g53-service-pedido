package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;


public class Produto {
	
	Categoria categoria;
	String nome;
	String descricao;
	BigDecimal valor;
	public Produto(){
		
	}
	public Produto(String nome) {
		this.nome = nome;
	}
	public Produto(Categoria categoria, String nome, String descricao, BigDecimal valor) {
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(categoria, descricao, nome, valor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return categoria == other.categoria && Objects.equals(descricao, other.descricao)
				&& Objects.equals(nome, other.nome) && Objects.equals(valor, other.valor);
	}	
	
}
