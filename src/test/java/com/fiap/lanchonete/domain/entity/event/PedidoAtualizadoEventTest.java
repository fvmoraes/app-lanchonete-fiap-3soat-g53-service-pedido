package com.fiap.lanchonete.domain.entity.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoAtualizadoEventTest {

    @Test
    public void testPedidoAtualizadoEvent() {
        int id = 1;
        StatusPedido status = StatusPedido.PRONTO;

        // Testa construtor e métodos de acesso
        PedidoAtualizadoEvent evento = new PedidoAtualizadoEvent(id, status);
        assertEquals(id, evento.getId());
        assertEquals(status, evento.getStatus());

        // Testa os métodos setters
        int novoId = 2;
        StatusPedido novoStatus = StatusPedido.FINALIZADO;
        evento.setId(novoId);
        evento.setStatus(novoStatus);
        assertEquals(novoId, evento.getId());
        assertEquals(novoStatus, evento.getStatus());
    }
}
