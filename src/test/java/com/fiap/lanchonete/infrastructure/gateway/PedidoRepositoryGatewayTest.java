package com.fiap.lanchonete.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoRepositoryGatewayTest {

    private PedidoRepository repository;
    private PedidoEntityMapper mapper;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    public void setup() {
        repository = mock(PedidoRepository.class);
        mapper = mock(PedidoEntityMapper.class);
        pedidoGateway = new PedidoRepositoryGateway(repository, mapper);
    }

    @Test
    public void testCriaPedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(repository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        Pedido result = pedidoGateway.criaPedido(pedido);

        assertNotNull(result);
        assertEquals(pedido, result);
        verify(repository).save(pedidoEntity);
    }

    @Test
    public void testAtualizaPedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);

        pedidoGateway.atualizaPedido(pedido);

        verify(repository).save(pedidoEntity);
    }

    @Test
    public void testBuscaPedidos() {
        List<PedidoEntity> pedidosEntities = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        when(repository.findAllByStatusPedidoOrderById(StatusPedido.PRONTO)).thenReturn(pedidosEntities);
        when(repository.findAllByStatusPedidoOrderById(StatusPedido.PREPARACAO)).thenReturn(pedidosEntities);
        when(repository.findAllByStatusPedidoOrderById(StatusPedido.RECEBIDO)).thenReturn(pedidosEntities);

        List<Pedido> result = pedidoGateway.buscaPedidos();

        assertNotNull(result);
        assertEquals(pedidos, result);
    }
    
    @Test
    public void testBuscaPedidoId() {
        Integer id = 1;
        PedidoEntity pedidoEntity = new PedidoEntity();
        Optional<PedidoEntity> optionalPedidoEntity = Optional.of(pedidoEntity);
        Pedido pedido = new Pedido();
        when(repository.findById(id)).thenReturn(optionalPedidoEntity);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        Pedido result = pedidoGateway.buscaPedidoId(id);

        assertNotNull(result);
        assertEquals(pedido, result);
    }

    @Test
    public void testBuscaPedidosStatus() {
        StatusPedido status = StatusPedido.PRONTO;
      
        List<PedidoEntity> pedidosEntities = new ArrayList<>();
        PedidoEntity pedido11 = new PedidoEntity(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        PedidoEntity pedido12 = new PedidoEntity(2, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        pedidosEntities.add(pedido11);
        pedidosEntities.add(pedido12);
        
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido1 = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        Pedido pedido2 = new Pedido(2, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        
        when(repository.findAllByStatusPedidoOrderById(status)).thenReturn(pedidosEntities);
        when(mapper.paraObjetoDominio(pedido11)).thenReturn(pedido1);
        when(mapper.paraObjetoDominio(pedido12)).thenReturn(pedido2);

        List<Pedido> result = pedidoGateway.buscaPedidosStatus(status);
        assertNotNull(result);
        assertEquals(pedidos, result);
    }
}