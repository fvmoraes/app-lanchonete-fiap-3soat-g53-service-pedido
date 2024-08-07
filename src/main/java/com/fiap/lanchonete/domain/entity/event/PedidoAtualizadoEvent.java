package com.fiap.lanchonete.domain.entity.event;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoAtualizadoEvent {
	int id;
	StatusPedido status;
	public PedidoAtualizadoEvent(){
		
	}
	public PedidoAtualizadoEvent(int id, StatusPedido status) {
		super();
		this.id = id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
}
