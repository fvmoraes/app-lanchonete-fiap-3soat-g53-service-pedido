package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.usecases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

public interface ProdutoUseCases {
	
	public List<Produto> buscarProdutos();
	public List<Produto> buscarProdutosCategoria(Categoria categoria);
	public void cadastraProduto(Produto produto) throws ProdutoJaCadastradoException;
	public void atualizaProduto(Produto produto) throws ProdutoNaoEncontradoException;
	public Produto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException;
	public void deletaProduto(String nome);

}
