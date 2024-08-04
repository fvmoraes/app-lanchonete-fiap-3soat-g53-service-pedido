package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;

public class ProdutoUseCasesImp implements ProdutoUseCases{

	private final ProdutoGateway produtoGateway;

	public ProdutoUseCasesImp(ProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	@Override
	public List<Produto> buscarProdutos() {
		return produtoGateway.buscarTodos();
	}

	@Override
	public List<Produto> buscarProdutosCategoria(Categoria categoria) {
		return produtoGateway.buscarPorCategoria(categoria);
	}
	
	@Override
	public void cadastraProduto(Produto produto) throws ProdutoJaCadastradoException {
		
		if (produtoGateway.buscarPeloNome(produto.getNome()) != null)
			throw new ProdutoJaCadastradoException();
		sanitizeInput(produto.getDescricao());
		produtoGateway.salvar(produto);
	}
    private String sanitizeInput(String input) {
 	   if (input == null) {
            return null;
        } 
 	String newString = input.replaceAll("[^a-zA-Z0-9]", "");
     return newString.replaceAll("(?i)AND|and|OR|or|\"|'|;|=|SELECT|select|WHERE|where|-", "");
 }
	@Override
	public void atualizaProduto(Produto produto) throws ProdutoNaoEncontradoException {
		
		if (produtoGateway.buscarPeloNome(produto.getNome()) == null)
			throw new ProdutoNaoEncontradoException();
		produtoGateway.salvar(produto);
	}

	@Override
	public Produto buscaProdutoNome(String nome) throws ProdutoNaoEncontradoException {
		Produto produto = produtoGateway.buscarPeloNome(nome);
			if (produto == null)
					throw new ProdutoNaoEncontradoException();
			return produto;
	}

	@Override
	public void deletaProduto(String nome) {
		produtoGateway.deletaProduto(nome);

	}

}
