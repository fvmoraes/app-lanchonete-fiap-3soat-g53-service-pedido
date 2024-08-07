package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testConstrutor() {
        // Testa o construtor e os métodos de acesso
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPedido statusPedido = StatusPedido.RECEBIDO;
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.valueOf(15.50);
        Pedido pedido = new Pedido(1, listaProdutos, statusPedido, statusPagamento, valorTotal);

        assertEquals(Integer.valueOf(1), pedido.getId());
        assertEquals(listaProdutos, pedido.getListaProdutos());
        assertEquals(statusPedido, pedido.getStatusPedido());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
        assertEquals(valorTotal, pedido.getValorTotal());
    }

    @Test
    public void testSetters() {
        // Testa os métodos setters
        Pedido pedido = new Pedido();
        List<Produto> novaListaProdutos = new ArrayList<>();
        Produto produto1 = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer de carne", BigDecimal.valueOf(10.50));
        novaListaProdutos.add(produto1);
        pedido.setListaProdutos(novaListaProdutos);
        assertEquals(novaListaProdutos, pedido.getListaProdutos());

        StatusPedido novoStatusPedido = StatusPedido.PREPARACAO;
        pedido.setStatusPedido(novoStatusPedido);
        assertEquals(novoStatusPedido, pedido.getStatusPedido());

        StatusPagamento novoStatusPagamento = StatusPagamento.PAGO;
        pedido.setStatusPagamento(novoStatusPagamento);
        assertEquals(novoStatusPagamento, pedido.getStatusPagamento());

        BigDecimal novoValorTotal = BigDecimal.valueOf(20.00);
        pedido.setValorTotal(novoValorTotal);
        assertEquals(novoValorTotal, pedido.getValorTotal());
    }
    
}
