package com.fiap.lanchonete.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

class PedidoRequestTest {

    private PedidoRequest pedidoRequest;

    @BeforeEach
    void setUp() {
        pedidoRequest = new PedidoRequest();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(pedidoRequest);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPedido statusPedido = StatusPedido.RECEBIDO;
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.valueOf(30.50);

        // Act
        pedidoRequest.setIdPedido(idPedido);
        pedidoRequest.setListaProdutos(listaProdutos);
        pedidoRequest.setStatusPedido(statusPedido);
        pedidoRequest.setStatusPagamento(statusPagamento);
        pedidoRequest.setValorTotal(valorTotal);

        // Assert
        assertEquals(idPedido, pedidoRequest.getIdPedido());
        assertEquals(listaProdutos, pedidoRequest.getListaProdutos());
        assertEquals(statusPedido, pedidoRequest.getStatusPedido());
        assertEquals(statusPagamento, pedidoRequest.getStatusPagamento());
        assertEquals(valorTotal, pedidoRequest.getValorTotal());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPedido statusPedido = StatusPedido.RECEBIDO;
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

        // Act
        PedidoRequest pedido = new PedidoRequest(idPedido, listaProdutos, statusPedido, statusPagamento);

        // Assert
        assertEquals(idPedido, pedido.getIdPedido());
        assertEquals(listaProdutos, pedido.getListaProdutos());
        assertEquals(statusPedido, pedido.getStatusPedido());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
        assertNull(pedido.getValorTotal());
    }
}
