package com.fiap.lanchonete.infrastructure.persistence.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

class ProdutoListConverterTest {

    private ObjectMapper objectMapper;
    private ProdutoListConverter converter;

    @BeforeEach
    void setUp() {
        objectMapper = mock(ObjectMapper.class);
        converter = new ProdutoListConverter();
    }

    @Test
    void convertToDatabaseColumn_Success() throws JsonProcessingException {

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(Categoria.Lanche, "Hamburguer","Um Hamburguer", BigDecimal.valueOf(10.99)));
        produtos.add(new Produto(Categoria.Lanche,"Batata Frita","Uma Batata Frita",BigDecimal.valueOf( 5.99)));

        String expectedJson = "[{\"categoria\":\"Lanche\",\"nome\":\"Hamburguer\",\"descricao\":\"Um Hamburguer\",\"valor\":10.99},{\"categoria\":\"Lanche\",\"nome\":\"Batata Frita\",\"descricao\":\"Uma Batata Frita\",\"valor\":5.99}]";
        when(objectMapper.writeValueAsString(any())).thenReturn(expectedJson);

        String json = converter.convertToDatabaseColumn(produtos);

        assertEquals(expectedJson, json);
    }

    @Test
    void convertToEntityAttribute_Success() throws IOException {
        String json = "[{\"categoria\":\"Lanche\",\"nome\":\"Hamburguer\",\"descricao\":\"Um Hamburguer\",\"valor\":10.99},{\"categoria\":\"Lanche\",\"nome\":\"Batata Frita\",\"descricao\":\"Uma Batata Frita\",\"valor\":5.99}]";
        List<Produto> expectedProdutos = new ArrayList<>();
        expectedProdutos.add(new Produto(Categoria.Lanche, "Hamburguer","Um Hamburguer", BigDecimal.valueOf(10.99)));
        expectedProdutos.add(new Produto(Categoria.Lanche,"Batata Frita","Uma Batata Frita",BigDecimal.valueOf( 5.99)));

        when(objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, Produto.class))).thenReturn(expectedProdutos);

        List<Produto> produtos = converter.convertToEntityAttribute(json);

        assertEquals(expectedProdutos, produtos);
    }
}
