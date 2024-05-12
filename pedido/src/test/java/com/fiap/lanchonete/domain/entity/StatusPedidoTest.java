package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusPedidoTest {

    @Test
    public void testStatusPedidoValues() {
        assertEquals("RECEBIDO", StatusPedido.RECEBIDO.name());
        assertEquals("PREPARACAO", StatusPedido.PREPARACAO.name());
        assertEquals("PRONTO", StatusPedido.PRONTO.name());
        assertEquals("FINALIZADO", StatusPedido.FINALIZADO.name());
    }
}
