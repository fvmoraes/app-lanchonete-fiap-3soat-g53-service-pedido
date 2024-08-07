package com.fiap.lanchonete.infrastructure.requestsdto;

import java.math.BigDecimal;
import java.util.List;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoRequest {
	
	Integer idPedido;
	List<Produto> listaProdutos;
	StatusPedido statusPedido;
	StatusPagamento statusPagamento;
	BigDecimal valorTotal;

	public PedidoRequest() {
		
	}
	
	public PedidoRequest(Integer idPedido, List<Produto> listaProdutos, StatusPedido statusPedido,
			StatusPagamento statusPagamento) {
		this.idPedido = idPedido;
		this.listaProdutos = listaProdutos;
		this.statusPedido = statusPedido;
		this.statusPagamento = statusPagamento;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
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

	
}
