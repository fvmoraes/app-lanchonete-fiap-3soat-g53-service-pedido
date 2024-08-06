package com.fiap.lanchonete.domain.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusPagamentoTest {

    @Test
    public void testStatusPagamentoValues() {
        assertEquals("PAGO", StatusPagamento.PAGO.name());
        assertEquals("PENDENTE", StatusPagamento.PENDENTE.name());
        assertEquals("CANCELADO", StatusPagamento.CANCELADO.name());
    }
}
