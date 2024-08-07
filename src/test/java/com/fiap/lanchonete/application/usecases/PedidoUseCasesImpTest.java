package com.fiap.lanchonete.application.usecases;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoUseCasesImpTest {

    private PedidoGateway pedidoGateway;
    private ProdutoGateway produtoGateway;
    private PedidoUseCasesImp pedidoUseCases;

    @BeforeEach
    public void setup() {
        pedidoGateway = mock(PedidoGateway.class);
        produtoGateway = mock(ProdutoGateway.class);
        pedidoUseCases = new PedidoUseCasesImp(pedidoGateway, produtoGateway);
    }

    @Test
    public void testBuscaPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        when(pedidoGateway.buscaPedidos()).thenReturn(pedidos);

        assertEquals(pedidos, pedidoUseCases.buscaPedidos());
    }

    @Test
    public void testBuscaPedidosPorStatus() {
        List<Pedido> pedidos = new ArrayList<>();
        StatusPedido status = StatusPedido.RECEBIDO;
        when(pedidoGateway.buscaPedidosStatus(status)).thenReturn(pedidos);

        assertEquals(pedidos, pedidoUseCases.buscaPedidosPorStatus(status));
    }

    @Test
    public void testAtualizaPedidoPagamento() {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        StatusPagamento novoStatus = StatusPagamento.PAGO;
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        Pedido pedidoAtualizado = pedidoUseCases.atualizaPedidoPagamento(novoStatus, 1);

        assertEquals(novoStatus, pedidoAtualizado.getStatusPagamento());
    }

    @Test
    public void testCalculaValorTotal() {
        List<BigDecimal> valores = List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        BigDecimal resultadoEsperado = BigDecimal.valueOf(60);

        assertEquals(resultadoEsperado, pedidoUseCases.calculaValorTotal(valores));
    }
    @Test
    public void testAtualizaPedido() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        pedidoUseCases.atualizaPedido(pedido);

        // Teste bem-sucedido se não lançar exceção
    }

    @Test
    public void testAtualizaPedidoNaoEncontrado() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(null);

        assertThrows(PedidoNaoEncontradoException.class,()-> pedidoUseCases.atualizaPedido(pedido));

    }

    @Test
    public void testAtualizaPedidoStatus() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        StatusPedido novoStatus = StatusPedido.PREPARACAO;
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        assertDoesNotThrow(() ->pedidoUseCases.atualizaPedidoStatus(1, novoStatus));
    }
    @Test
    public void testBuscaPedidoId() throws PedidoNaoEncontradoException {
        int id = 1;
        Pedido pedido = new Pedido(id, new ArrayList<>(), StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        when(pedidoGateway.buscaPedidoId(id)).thenReturn(pedido);

        assertEquals(pedido, pedidoUseCases.buscaPedidoId(id));
    }

    @Test
    public void testBuscaPedidoIdNotFound() throws PedidoNaoEncontradoException {
        int id = 1;
        when(pedidoGateway.buscaPedidoId(id)).thenReturn(null);

     assertThrows(PedidoNaoEncontradoException.class,()-> pedidoUseCases.buscaPedidoId(id));
    }
    
    @Test
    public void testRealizaPedido() throws PedidoComProdutoNaoCadastradoException  {
      	List<Produto> produto = new ArrayList<>();
        produto.add(new Produto(Categoria.Lanche,"Produto1","Descricao", BigDecimal.valueOf(2)));
        produto.add(new Produto(Categoria.Lanche,"Produto2","Descricao2", BigDecimal.valueOf(2)));
    	Pedido pedido = new Pedido(1, produto, StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        when(produtoGateway.buscarPeloNome("Produto1")).thenReturn(produto.get(0));
        when(produtoGateway.buscarPeloNome("Produto2")).thenReturn(produto.get(1));
        when(pedidoGateway.criaPedido(any(Pedido.class))).thenReturn(pedido);
        assertEquals(pedido, pedidoUseCases.realizaPedido(pedido));
    }

    @Test
    public void testRealizaPedidoProdutoNaoCadastrado() throws PedidoComProdutoNaoCadastradoException {
    	List<Produto> produto = new ArrayList<>();
        produto.add(new Produto("Produto1"));
        produto.add(new Produto("Produto2"));
        
    	Pedido pedido = new Pedido(1, produto, StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.ZERO);
        when(produtoGateway.buscarPeloNome("Produto1")).thenReturn(null);
        when(produtoGateway.buscarPeloNome("Produto2")).thenReturn(new Produto());

        
        assertThrows(PedidoComProdutoNaoCadastradoException.class,()-> pedidoUseCases.realizaPedido(pedido));

    }

}
