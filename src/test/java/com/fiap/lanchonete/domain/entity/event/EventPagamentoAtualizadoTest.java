package com.fiap.lanchonete.domain.entity.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class EventPagamentoAtualizadoTest {

    @Test
    public void testEventPagamentoAtualizado() {
        Integer idPedidoPago = 1;
        StatusPagamento statusPagamento = StatusPagamento.PAGO;


        EventPagamentoAtualizado evento = new EventPagamentoAtualizado(idPedidoPago, statusPagamento);
        assertEquals(idPedidoPago, evento.getIdPedidoPago());
        assertEquals(statusPagamento, evento.getStatusPagamento());

        Integer novoIdPedidoPago = 2;
        StatusPagamento novoStatusPagamento = StatusPagamento.CANCELADO;
        evento.setIdPedidoPago(novoIdPedidoPago);
        evento.setStatusPagamento(novoStatusPagamento);
        assertEquals(novoIdPedidoPago, evento.getIdPedidoPago());
        assertEquals(novoStatusPagamento, evento.getStatusPagamento());
    }
}
