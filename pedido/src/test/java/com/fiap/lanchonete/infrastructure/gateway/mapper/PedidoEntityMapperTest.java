package com.fiap.lanchonete.infrastructure.gateway.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

class PedidoEntityMapperTest {

    private PedidoEntityMapper pedidoEntityMapper;

    @BeforeEach
    void setUp() {
        pedidoEntityMapper = new PedidoEntityMapper();
    }

    @Test
    void paraPedidoEntity_Success() {
        // Arrange
        Pedido pedidoDomain = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.99));

        // Act
        PedidoEntity pedidoEntity = pedidoEntityMapper.paraPedidoEntity(pedidoDomain);

        // Assert
        assertEquals(pedidoDomain.getId(), pedidoEntity.getId());
        assertEquals(pedidoDomain.getListaProdutos(), pedidoEntity.getListaProdutosPedido());
        assertEquals(pedidoDomain.getStatusPedido(), pedidoEntity.getStatusPedido());
        assertEquals(pedidoDomain.getStatusPagamento(), pedidoEntity.getStatusPagamento());
        assertEquals(pedidoDomain.getValorTotal(), pedidoEntity.getValorTotal());
    }

    @Test
    void paraObjetoDominio_Success() {
        // Arrange
        List<Produto> produtosEntity = new ArrayList<>();
        PedidoEntity pedidoEntity = new PedidoEntity(1, produtosEntity, StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.99));

        // Act
        Pedido pedidoDomain = pedidoEntityMapper.paraObjetoDominio(pedidoEntity);

        // Assert
        assertEquals(pedidoEntity.getId(), pedidoDomain.getId());
        assertEquals(pedidoEntity.getListaProdutosPedido(), pedidoDomain.getListaProdutos());
        assertEquals(pedidoEntity.getStatusPedido(), pedidoDomain.getStatusPedido());
        assertEquals(pedidoEntity.getStatusPagamento(), pedidoDomain.getStatusPagamento());
        assertEquals(pedidoEntity.getValorTotal(), pedidoDomain.getValorTotal());
    }
}
