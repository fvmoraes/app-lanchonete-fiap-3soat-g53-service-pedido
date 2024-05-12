package com.fiap.lanchonete.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

class PedidoPagamentoResponseTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Integer idPedido = 1;
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.valueOf(20.50);

        // Act
        PedidoPagamentoResponse response = new PedidoPagamentoResponse(idPedido, statusPagamento, valorTotal);

        assertEquals(idPedido, response.getIdPedido());
        assertEquals(statusPagamento, response.getStatusPagamento());
        assertEquals(valorTotal, response.getValorTotal());
        response.setIdPedido(2);
        assertEquals(2, response.getIdPedido());
        response.setStatusPagamento(StatusPagamento.CANCELADO);
        assertEquals(StatusPagamento.CANCELADO, response.getStatusPagamento());
        response.setValorTotal(BigDecimal.valueOf(21.5));
        assertEquals(BigDecimal.valueOf(21.5), response.getValorTotal());

    }
}
