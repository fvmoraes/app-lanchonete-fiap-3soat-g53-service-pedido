package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoComProdutoNaoCadastradoException;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoPagamentoResponse;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {
	private static final String PEDIDO_EXCHANGE_1 = "pedido-exchange";
	private static final String PEDIDO_PAGAMENTO_ROUTING_KEY = "pedido-para-pagamento-routing-key";

	private final PedidoUseCases pedidoUseCases;
	private final PedidoRequestMapper mapper;
	private RabbitTemplate template;

	public PedidoController(PedidoUseCases pedidoUseCases, PedidoRequestMapper mapper, RabbitTemplate template) {
		this.pedidoUseCases = pedidoUseCases;
		this.mapper = mapper;
		this.template = template;
	}

	@GetMapping
	public List<PedidoResponse> buscaPedidos() {
		return pedidoUseCases.buscaPedidos().stream().map(mapper::paraResponse).toList();
	};

	@GetMapping("{id}")
	public ResponseEntity<PedidoResponse> buscaPedidosPorId(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(mapper.paraResponse(pedidoUseCases.buscaPedidoId(id)), HttpStatus.OK);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	};

	@GetMapping("pagamento/{id}")
	public ResponseEntity<PedidoPagamentoResponse> buscaPedidosPagamento(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(mapper.paraResponseDTO(pedidoUseCases.buscaPedidoId(id)), HttpStatus.OK);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	};

	@GetMapping("status/{status}")
	public List<PedidoResponse> buscaPedidosPorStatus(@PathVariable StatusPedido status) {
		return pedidoUseCases.buscaPedidosPorStatus(status).stream().map(mapper::paraResponse).toList();
	};


	@PostMapping
	public ResponseEntity<PedidoResponse> realizarPedido(@RequestBody PedidoRequest pedido) {
		try {
			pedido.getListaProdutos().forEach(produto -> validateInput(produto.getNome()));
			
			Pedido pedidoRealizado = pedidoUseCases.realizaPedido(mapper.paraObjetoDominio(pedido));

			PedidoRealizadoEvent pedidoRealizadoEvent = new PedidoRealizadoEvent(pedidoRealizado);
			template.convertAndSend(PEDIDO_EXCHANGE_1, PEDIDO_PAGAMENTO_ROUTING_KEY, pedidoRealizadoEvent);

			PedidoResponse response = mapper.paraResponse(pedidoRealizado);

			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

		} catch (PedidoComProdutoNaoCadastradoException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	private void validateInput(String input) {
		if (input == null) {
			return;
		}// 
		if (input.toUpperCase().matches(".*(?i)(AND|UNION|ALL|%|<|>|(|)|{|}|[|]|!|@|#|$|HTTP|WWW|\\.COM||OR|\"|\\|=|:|'|\'|;|=|SELECT|WHERE|-).*"))
			throw new IllegalArgumentException();
		}
}
