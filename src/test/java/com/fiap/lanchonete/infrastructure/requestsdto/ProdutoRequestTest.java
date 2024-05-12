package com.fiap.lanchonete.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;

class ProdutoRequestTest {

    private ProdutoRequest produtoRequest;

    @BeforeEach
    void setUp() {
        produtoRequest = new ProdutoRequest(Categoria.Bebida, "Refrigerante", "Delicioso refrigerante", BigDecimal.valueOf(5.99));
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(produtoRequest);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Categoria categoria = Categoria.Lanche;
        String nome = "Hamburguer";
        String descricao = "Delicioso hambúrguer";
        BigDecimal valor = BigDecimal.valueOf(10.99);

        // Act
        produtoRequest.setCategoria(categoria);
        produtoRequest.setNome(nome);
        produtoRequest.setDescricao(descricao);
        produtoRequest.setValor(valor);

        // Assert
        assertEquals(categoria, produtoRequest.getCategoria());
        assertEquals(nome, produtoRequest.getNome());
        assertEquals(descricao, produtoRequest.getDescricao());
        assertEquals(valor, produtoRequest.getValor());
    }
}
