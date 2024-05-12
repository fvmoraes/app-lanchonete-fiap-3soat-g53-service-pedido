package com.fiap.lanchonete.infrastructure.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.event.EventPagamentoAtualizado;
import com.fiap.lanchonete.domain.entity.event.PedidoAtualizadoEvent;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;

@Component
@RabbitListener(queues = RabbitConsumerPedidoQueueGateway.PEDIDO_QUEUE_1)
public class RabbitConsumerPedidoQueueGateway {
	private static final Logger log = LoggerFactory.getLogger(RabbitConsumerPedidoQueueGateway.class);

	private static final String PEDIDO_QUEUE_1 = "pedido-queue";
	private static final String PEDIDO_EXCHANGE_1 = "pedido-exchange";
	private static final String PEDIDO_PRODUCAO_ROUTING_KEY = "pedido-para-pruducao-routing-key";
	RabbitTemplate template;

	final PedidoUseCases pedidoUseCases;

	RabbitConsumerPedidoQueueGateway(PedidoUseCases pedidoUseCases, RabbitTemplate template) {
		this.pedidoUseCases = pedidoUseCases;
		this.template = template;
	}
	
	@RabbitHandler
	public void pagamentoRecebido(EventPagamentoAtualizado pagamentoAtualizado) throws PedidoNaoEncontradoException {
		try {
		log.info("Evento Pagamente Atualizado Recebido!");
		Pedido pedidoRealizado = pedidoUseCases.atualizaPedidoPagamento(pagamentoAtualizado.getStatusPagamento(),
				pagamentoAtualizado.getIdPedidoPago());
		PedidoRealizadoEvent pedidoRealizadoEvent = new PedidoRealizadoEvent(pedidoRealizado);
		log.info("Evento PedidoRealizadoEvent sendo enviado para producao!");

		template.convertAndSend(PEDIDO_EXCHANGE_1, PEDIDO_PRODUCAO_ROUTING_KEY, pedidoRealizadoEvent);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException("");
		}
	}

	@RabbitHandler
	public void atualizacaoStatusProducao(PedidoAtualizadoEvent atualizacao) throws PedidoNaoEncontradoException {
		try {
		pedidoUseCases.atualizaPedidoStatus(atualizacao.getId(), atualizacao.getStatus());
		log.info("Evento PedidoAtualizadoEvent recebido com sucesso!");
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException("");
		}
	}

}
