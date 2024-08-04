package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usecases.ProdutoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoJaCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.ProdutoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.infrastructure.mapper.ProdutoRequestMapper;
import com.fiap.lanchonete.infrastructure.requestsdto.ProdutoRequest;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {
	private static final String PRODUTO_DELETADO = "Produto deletado com sucesso";

	private final ProdutoUseCases produtoUseCases;
	private final ProdutoRequestMapper mapper;

	public ProdutoController(ProdutoUseCases produtoUseCases, ProdutoRequestMapper mapper) {
		this.produtoUseCases = produtoUseCases;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<String>> buscarProdutos() {
		return new ResponseEntity<>(produtoUseCases.buscarProdutos().stream().map(mapper::paraResponse).toList(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("{categoria}")
	public ResponseEntity<List<String>> buscarProdutosCategoria(@PathVariable Categoria categoria) {
		try {
			validateInput(categoria.toString());

			return new ResponseEntity<List<String>>(
					produtoUseCases.buscarProdutosCategoria(Categoria.valueOf(categoria.toString())).stream()
							.map(mapper::paraResponse).toList(),
					HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<String> criarProduto(@RequestBody ProdutoRequest produtoRequest) {

		try {
			validateInput(produtoRequest.getNome());
			validateInput(produtoRequest.getDescricao());

			produtoUseCases.cadastraProduto(mapper.paraObjetoDominio(produtoRequest));
			return new ResponseEntity<>("Produto cadastrado com sucesso", HttpStatus.CREATED);

		} catch (ProdutoJaCadastradoException e) {
			return new ResponseEntity<>("Produto já cadastrado", HttpStatus.CONFLICT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<String> atualizaProduto(@RequestBody ProdutoRequest produtoRequest) {
		try {
			Categoria.valueOf(produtoRequest.getCategoria().toString());

			validateInput(produtoRequest.getNome());
			validateInput(produtoRequest.getDescricao());

			produtoUseCases.atualizaProduto(mapper.paraObjetoDominio(produtoRequest));
			return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);

		} catch (ProdutoNaoEncontradoException e) {
			return new ResponseEntity<>("Produto não cadastrado", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletaProduto(@RequestBody ProdutoRequest produtoRequest) {
		validateInput(produtoRequest.getNome());

		produtoUseCases.deletaProduto(produtoRequest.getNome());
		return new ResponseEntity<>(PRODUTO_DELETADO, HttpStatus.OK);
	}

	@DeleteMapping("{nome}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<String> deletaProdutoNome(@PathVariable String nome) {
		validateInput(nome);
		produtoUseCases.deletaProduto(nome);
		return new ResponseEntity<>(PRODUTO_DELETADO, HttpStatus.OK);
	}

	private void validateInput(String input) {
		if (input == null) {
			return;
		}// 
		if (input.toUpperCase().matches(".*(?i)(AND|UNION|ALL|%|<|>|(|)|{|}|[|]|!|@|#|$|HTTP|WWW|\\.COM||OR|\"|\\|=|:|'|\'|;|=|SELECT|WHERE|-).*"))
			throw new IllegalArgumentException();
		}
	
}
