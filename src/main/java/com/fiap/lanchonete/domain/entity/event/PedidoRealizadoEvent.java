package com.fiap.lanchonete.domain.entity.event;

import java.util.Objects;

import com.fiap.lanchonete.domain.entity.Pedido;

public class PedidoRealizadoEvent {
	Pedido pedidoRealizado;

	public Pedido getPedidoRealizado() {
		return pedidoRealizado;
	}

	public void setPedidoRealizado(Pedido pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}

	public PedidoRealizadoEvent(Pedido pedidoRealizado) {
		super();
		this.pedidoRealizado = pedidoRealizado;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoRealizadoEvent that = (PedidoRealizadoEvent) o;
        return Objects.equals(pedidoRealizado, that.pedidoRealizado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoRealizado);
    }
	
}
