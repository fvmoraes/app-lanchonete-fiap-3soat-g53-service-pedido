package com.fiap.lanchonete.domain.entity.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoRealizadoEventTest {

    @Test
    public void testPedidoRealizadoEvent() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto1 = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer de carne", BigDecimal.valueOf(10.50));
        listaProdutos.add(produto1);

        Pedido pedido = new Pedido(1, listaProdutos, StatusPedido.RECEBIDO, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.50));

        PedidoRealizadoEvent evento = new PedidoRealizadoEvent(pedido);
        assertEquals(pedido, evento.getPedidoRealizado());

        Pedido novoPedido = new Pedido(2, listaProdutos, StatusPedido.PREPARACAO, StatusPagamento.PAGO, BigDecimal.valueOf(20.00));
        evento.setPedidoRealizado(novoPedido);
        assertEquals(novoPedido, evento.getPedidoRealizado());
    }
}
