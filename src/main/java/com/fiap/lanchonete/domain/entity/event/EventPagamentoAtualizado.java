package com.fiap.lanchonete.domain.entity.event;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventPagamentoAtualizado {
	Integer idPedidoPago;
	StatusPagamento statusPagamento;
	
	public EventPagamentoAtualizado(Integer idPedidoPago, StatusPagamento statusPagamento) {
		super();
		this.idPedidoPago = idPedidoPago;
		this.statusPagamento = statusPagamento;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Integer getIdPedidoPago() {
		return idPedidoPago;
	}

	public void setIdPedidoPago(Integer idPedidoPago) {
		this.idPedidoPago = idPedidoPago;
	}

}