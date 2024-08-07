package com.fiap.lanchonete.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

class PedidoResponseTest {

    private PedidoResponse pedidoResponse;

    @BeforeEach
    void setUp() {
        pedidoResponse = new PedidoResponse(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.valueOf(30.50));
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(pedidoResponse);
    }

    @Test
    void testGettersAndSetters() {
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPedido statusPedido = StatusPedido.PRONTO;
        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        BigDecimal valorTotal = BigDecimal.valueOf(50.25);

        pedidoResponse.setIdPedido(idPedido);
        pedidoResponse.setListaProdutos(listaProdutos);
        pedidoResponse.setStatusPedido(statusPedido);
        pedidoResponse.setStatusPagamento(statusPagamento);
        pedidoResponse.setValorTotal(valorTotal);

        assertEquals(idPedido, pedidoResponse.getIdPedido());
        assertEquals(listaProdutos, pedidoResponse.getListaProdutos());
        assertEquals(statusPedido, pedidoResponse.getStatusPedido());
        assertEquals(statusPagamento, pedidoResponse.getStatusPagamento());
        assertEquals(valorTotal, pedidoResponse.getValorTotal());
    }
}
