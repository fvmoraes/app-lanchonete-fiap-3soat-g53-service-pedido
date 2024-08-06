package com.fiap.lanchonete.infrastructure.gateway;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.gateway.mapper.ProdutoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.ProdutoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

public class ProdutoRespositoryGatewayTest {

	private ProdutoRepository repository;
	private ProdutoEntityMapper mapper;
	private ProdutoGateway produtoGateway;

	@BeforeEach
	public void setup() {
		repository = mock(ProdutoRepository.class);
		mapper = mock(ProdutoEntityMapper.class);
		produtoGateway = new ProdutoRespositoryGateway(repository, mapper);
	}

	@Test
	public void testBuscarTodos() {
		List<ProdutoEntity> produtosEntities = new ArrayList<>();
		ProdutoEntity produto11 = new ProdutoEntity(Categoria.Lanche, "A", "a", BigDecimal.ONE);
		ProdutoEntity produto21 = new ProdutoEntity(Categoria.Lanche, "B", "a", BigDecimal.ONE);
		produtosEntities.add(produto11);
		produtosEntities.add(produto21);

		List<Produto> produtos = new ArrayList<>();
		Produto produto1 = new Produto(Categoria.Lanche, "A", "a", BigDecimal.ONE);
		Produto produto2 = new Produto(Categoria.Lanche, "B", "a", BigDecimal.ONE);
		;
		produtos.add(produto1);
		produtos.add(produto2);

		when(mapper.paraObjetoDominio(produtosEntities.get(0))).thenReturn(produtos.get(0));
		when(mapper.paraObjetoDominio(produtosEntities.get(1))).thenReturn(produtos.get(1));

		when(repository.findAll()).thenReturn(produtosEntities);

		List<Produto> result = produtoGateway.buscarTodos();

		assertNotNull(result);
		assertEquals(produtos, result);
	}

	@Test
	public void testBuscarPeloNomeExistente() {
		String nome = "Hamburguer";
		ProdutoEntity produtoEntity = new ProdutoEntity();
		Produto produto = new Produto();
		when(repository.findByNome(nome)).thenReturn(produtoEntity);
		when(mapper.paraObjetoDominio(produtoEntity)).thenReturn(produto);

		Produto result = produtoGateway.buscarPeloNome(nome);

		assertNotNull(result);
		assertEquals(produto, result);
	}

	@Test
	public void testBuscarPeloNomeNaoExistente() {
		String nome = "Hamburguer";
		when(repository.findByNome(nome)).thenReturn(null);

		Produto result = produtoGateway.buscarPeloNome(nome);

		assertNull(result);
	}

	@Test
	public void testSalvar() {
		Produto produto = new Produto();
		ProdutoEntity produtoEntity = new ProdutoEntity();
		when(mapper.paraProdutoEntity(produto)).thenReturn(produtoEntity);
		when(repository.save(produtoEntity)).thenReturn(produtoEntity);
		when(mapper.paraObjetoDominio(produtoEntity)).thenReturn(produto);

		Produto result = produtoGateway.salvar(produto);

		assertNotNull(result);
		assertEquals(produto, result);
	}

	@Test
	public void testBuscarPorCategoria() {

		Categoria categoria = Categoria.Lanche;
		List<ProdutoEntity> produtosEntities = new ArrayList<>();
		ProdutoEntity produto11 = new ProdutoEntity(Categoria.Lanche, "A", "a", BigDecimal.ONE);
		ProdutoEntity produto21 = new ProdutoEntity(Categoria.Lanche, "B", "a", BigDecimal.ONE);
		produtosEntities.add(produto11);
		produtosEntities.add(produto21);

		List<Produto> produtos = new ArrayList<>();
		Produto produto1 = new Produto(Categoria.Lanche, "A", "a", BigDecimal.ONE);
		Produto produto2 = new Produto(Categoria.Lanche, "B", "a", BigDecimal.ONE);
		;
		produtos.add(produto1);
		produtos.add(produto2);
		when(repository.findByCategoria(categoria)).thenReturn(produtosEntities);

		when(mapper.paraObjetoDominio(produtosEntities.get(0))).thenReturn(produtos.get(0));
		when(mapper.paraObjetoDominio(produtosEntities.get(1))).thenReturn(produtos.get(1));
		List<Produto> result = produtoGateway.buscarPorCategoria(categoria);

		assertNotNull(result);
		assertEquals(produtos, result);
	}

	@Test
	public void testDeletaProduto() {
		String nome = "Hamburguer";
		ProdutoEntity produtoEntity = new ProdutoEntity();
		when(repository.findByNome(nome)).thenReturn(produtoEntity);

		produtoGateway.deletaProduto(nome);

		verify(repository).delete(produtoEntity);
	}
}

