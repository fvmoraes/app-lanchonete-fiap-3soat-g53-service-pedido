package com.fiap.lanchonete.infrastructure.persistence.entity;

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

class PedidoEntityTest {

    private PedidoEntity pedidoEntity;
    private final List<Produto> produtos = new ArrayList<>();
    private final StatusPedido statusPedido = StatusPedido.RECEBIDO;
    private final StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
    private final BigDecimal valorTotal = BigDecimal.valueOf(20.50);

    @BeforeEach
    void setUp() {
        pedidoEntity = new PedidoEntity(produtos, statusPedido, statusPagamento, valorTotal);
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(pedidoEntity);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Integer id = 1;
        List<Produto> produtosAtualizados = new ArrayList<>();
        StatusPedido statusPedidoAtualizado = StatusPedido.RECEBIDO;
        StatusPagamento statusPagamentoAtualizado = StatusPagamento.PAGO;
        BigDecimal valorTotalAtualizado = BigDecimal.valueOf(30.75);

        // Act
        pedidoEntity.setId(id);
        pedidoEntity.setListaProdutosPedido(produtosAtualizados);
        pedidoEntity.setStatusPedido(statusPedidoAtualizado);
        pedidoEntity.setStatusPagamento(statusPagamentoAtualizado);
        pedidoEntity.setValorTotal(valorTotalAtualizado);

        // Assert
        assertEquals(id, pedidoEntity.getId());
        assertEquals(produtosAtualizados, pedidoEntity.getListaProdutosPedido());
        assertEquals(statusPedidoAtualizado, pedidoEntity.getStatusPedido());
        assertEquals(statusPagamentoAtualizado, pedidoEntity.getStatusPagamento());
        assertEquals(valorTotalAtualizado, pedidoEntity.getValorTotal());
    }
}
