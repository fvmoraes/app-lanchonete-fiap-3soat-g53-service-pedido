package com.fiap.lanchonete.infrastructure.gateway.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

class ProdutoEntityMapperTest {

    private ProdutoEntityMapper produtoEntityMapper;

    @BeforeEach
    void setUp() {
        produtoEntityMapper = new ProdutoEntityMapper();
    }

    @Test
    void paraProdutoEntity_Success() {
        Produto produtoDomain = new Produto(Categoria.Lanche, "Hamburguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.99));

        ProdutoEntity produtoEntity = produtoEntityMapper.paraProdutoEntity(produtoDomain);

        assertEquals(produtoDomain.getCategoria(), produtoEntity.getCategoria());
        assertEquals(produtoDomain.getNome(), produtoEntity.getNome());
        assertEquals(produtoDomain.getDescricao(), produtoEntity.getDescricao());
        assertEquals(produtoDomain.getValor(), produtoEntity.getValor());
    }

    @Test
    void paraObjetoDominio_Success() {
        ProdutoEntity produtoEntity = new ProdutoEntity(Categoria.Lanche, "Hamburguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.99));

        Produto produtoDomain = produtoEntityMapper.paraObjetoDominio(produtoEntity);

        assertEquals(produtoEntity.getCategoria(), produtoDomain.getCategoria());
        assertEquals(produtoEntity.getNome(), produtoDomain.getNome());
        assertEquals(produtoEntity.getDescricao(), produtoDomain.getDescricao());
        assertEquals(produtoEntity.getValor(), produtoDomain.getValor());
    }
}
