package com.fiap.lanchonete.application.usecases;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

public class ProdutoUseCasesImpTest {

    private ProdutoGateway produtoGateway;
    private ProdutoUseCasesImp produtoUseCases;

    @BeforeEach
    public void setup() {
        produtoGateway = mock(ProdutoGateway.class);
        produtoUseCases = new ProdutoUseCasesImp(produtoGateway);
    }

    @Test
    public void testBuscarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        when(produtoGateway.buscarTodos()).thenReturn(produtos);

        assertEquals(produtos, produtoUseCases.buscarProdutos());
    }
    @Test
    public void testCadastraProdutoJaCadastrado() throws ProdutoJaCadastradoException {
        Produto produto = new Produto(Categoria.Lanche, "Hamburguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.99));
        when(produtoGateway.buscarPeloNome(produto.getNome())).thenReturn(produto);

        assertThrows(ProdutoJaCadastradoException.class, () -> produtoUseCases.cadastraProduto(produto));
    }

    @Test
    public void testBuscaProdutoNomeEncontrado() throws ProdutoNaoEncontradoException {
        String nome = "Hamburguer";
        Produto produto = new Produto(Categoria.Lanche, nome, "Delicioso hambúrguer", BigDecimal.valueOf(10.99));
        when(produtoGateway.buscarPeloNome(nome)).thenReturn(produto);

        assertEquals(produto, produtoUseCases.buscaProdutoNome(nome));
    }



    @Test
    public void testBuscarProdutosCategoria() {
        List<Produto> produtos = new ArrayList<>();
        Categoria categoria = Categoria.Lanche;
        when(produtoGateway.buscarPorCategoria(categoria)).thenReturn(produtos);

        assertEquals(produtos, produtoUseCases.buscarProdutosCategoria(categoria));
    }
    @Test
    public void testCadastraProduto() throws ProdutoJaCadastradoException {
        Produto produto = new Produto(Categoria.Lanche, "Hamburguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.99));
        when(produtoGateway.buscarPeloNome(produto.getNome())).thenReturn(null);

        assertDoesNotThrow(() ->produtoUseCases.cadastraProduto(produto));
    }
    @Test
    public void testBuscaProdutoNomeNaoEncontrado() throws ProdutoNaoEncontradoException {
        String nome = "Hamburguer";
        when(produtoGateway.buscarPeloNome(nome)).thenReturn(null);

       
        assertThrows(ProdutoNaoEncontradoException.class,() -> produtoUseCases.buscaProdutoNome(nome));

    }

    @Test
    public void testDeletaProduto() {
        String nome = "Hamburguer";

        assertDoesNotThrow(() -> produtoUseCases.deletaProduto(nome));
    }
}
