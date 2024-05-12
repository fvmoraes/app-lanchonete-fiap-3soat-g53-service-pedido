package com.fiap.lanchonete.domain.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;


public class ProdutoTest {

    @Test
    public void testProdutoAccessors() {
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer de carne", BigDecimal.valueOf(10.50));

        assertEquals(Categoria.Lanche, produto.getCategoria());
        assertEquals("Hambúrguer", produto.getNome());
        assertEquals("Delicioso hambúrguer de carne", produto.getDescricao());
        assertEquals(BigDecimal.valueOf(10.50), produto.getValor());
    }
}
