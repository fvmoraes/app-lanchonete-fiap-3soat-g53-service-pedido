package com.fiap.lanchonete.infrastructure.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoPagamentoResponse;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

public class PedidoRequestMapper {
	
	public Pedido paraObjetoDominio(PedidoRequest pedidoEntity) {
		return new Pedido(pedidoEntity.getIdPedido(),pedidoEntity.getListaProdutos(),
				pedidoEntity.getStatusPedido(), pedidoEntity.getStatusPagamento(), pedidoEntity.getValorTotal());

	}
	
	public PedidoResponse paraResponse(Pedido pedido) {
		return new PedidoResponse(pedido.getId(), pedido.getListaProdutos(),
				pedido.getStatusPedido(), pedido.getStatusPagamento(), pedido.getValorTotal());
	}
	public PedidoPagamentoResponse paraResponseDTO(Pedido pedido) {
		return new PedidoPagamentoResponse(pedido.getId(), pedido.getStatusPagamento(), pedido.getValorTotal());
	}
}
