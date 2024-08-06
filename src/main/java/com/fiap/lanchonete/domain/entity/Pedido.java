package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pedido {
	
	Integer id;
	List<Produto> listaProdutos;
	StatusPedido statusPedido;
	StatusPagamento statusPagamento;
	BigDecimal valorTotal;
	
	public Pedido(Integer id, List<Produto> listaProdutos, StatusPedido statusPedido,
			StatusPagamento statusPagamento,  BigDecimal valorTotal) {
	
		this.id = id;
		this.listaProdutos = listaProdutos;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
		this.valorTotal = valorTotal;
	}
	
	public Pedido() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, listaProdutos, statusPagamento, statusPedido, valorTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id) && Objects.equals(listaProdutos, other.listaProdutos)
				&& statusPagamento == other.statusPagamento && statusPedido == other.statusPedido
				&& Objects.equals(valorTotal, other.valorTotal);
	}

}
