package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CategoriaTest {

    @Test
    public void testCategoriaValues() {
        assertEquals("Lanche", Categoria.Lanche.name());
        assertEquals("Bebida", Categoria.Bebida.name());
        assertEquals("Acompanhamento", Categoria.Acompanhamento.name());
        assertEquals("Sobremesa", Categoria.Sobremesa.name());
    }
}
