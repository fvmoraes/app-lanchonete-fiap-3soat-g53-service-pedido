package com.fiap.lanchonete.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.requestsdto.ProdutoRequest;

class ProdutoRequestMapperTest {

    private ProdutoRequestMapper produtoRequestMapper;

    @BeforeEach
    void setUp() {
        produtoRequestMapper = new ProdutoRequestMapper();
    }

    @Test
    void testParaObjetoDominio() {
        ProdutoRequest produtoRequest = new ProdutoRequest(Categoria.Lanche, "Hamburguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.99));

        Produto produto = produtoRequestMapper.paraObjetoDominio(produtoRequest);

        assertNotNull(produto);
        assertEquals(produtoRequest.getCategoria(), produto.getCategoria());
        assertEquals(produtoRequest.getNome(), produto.getNome());
        assertEquals(produtoRequest.getDescricao(), produto.getDescricao());
        assertEquals(produtoRequest.getValor(), produto.getValor());
    }

    @Test
    void testParaResponse() {
        // Arrange
        Produto produto = new Produto(Categoria.Bebida, "Coca-Cola", "Refrigerante", BigDecimal.valueOf(5.99));

        // Act
        String produtoResponse = produtoRequestMapper.paraResponse(produto);

        // Assert
        assertNotNull(produtoResponse);
        assertTrue(produtoResponse.contains(produto.getCategoria().toString()));
        assertTrue( produtoResponse.contains(produto.getNome()));
        assertTrue(produtoResponse.contains(produto.getDescricao()));
        assertTrue(produtoResponse.contains(String.valueOf(produto.getValor())));
    }
}
