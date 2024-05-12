package com.fiap.lanchonete.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;

class ProdutoEntityTest {

    private ProdutoEntity produtoEntity;
    private final Categoria categoria = Categoria.Bebida;
    private final String nome = "Coca-Cola";
    private final String descricao = "Refrigerante";
    private final BigDecimal valor = BigDecimal.valueOf(5.99);

    @BeforeEach
    void setUp() {
        produtoEntity = new ProdutoEntity(categoria, nome, descricao, valor);
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(produtoEntity);
    }

    @Test
    void testGetters() {
        assertEquals(categoria, produtoEntity.getCategoria());
        assertEquals(nome, produtoEntity.getNome());
        assertEquals(descricao, produtoEntity.getDescricao());
        assertEquals(valor, produtoEntity.getValor());
    }

    @Test
    void testSetters() {
        // Arrange
        Categoria categoriaAtualizada = Categoria.Lanche;
        String nomeAtualizado = "Hamburguer";
        String descricaoAtualizada = "Delicioso hambúrguer";
        BigDecimal valorAtualizado = BigDecimal.valueOf(10.99);

        // Act
        produtoEntity.setCategoria(categoriaAtualizada);
        produtoEntity.setNome(nomeAtualizado);
        produtoEntity.setDescricao(descricaoAtualizada);
        produtoEntity.setValor(valorAtualizado);

        // Assert
        assertEquals(categoriaAtualizada, produtoEntity.getCategoria());
        assertEquals(nomeAtualizado, produtoEntity.getNome());
        assertEquals(descricaoAtualizada, produtoEntity.getDescricao());
        assertEquals(valorAtualizado, produtoEntity.getValor());
    }
}
