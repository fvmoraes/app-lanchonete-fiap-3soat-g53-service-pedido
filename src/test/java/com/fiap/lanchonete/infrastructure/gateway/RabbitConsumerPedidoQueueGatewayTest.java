package com.fiap.lanchonete.infrastructure.gateway;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.domain.entity.event.EventPagamentoAtualizado;
import com.fiap.lanchonete.domain.entity.event.PedidoAtualizadoEvent;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;

public class RabbitConsumerPedidoQueueGatewayTest {
		private Logger log;
	    private RabbitTemplate template;
	    private PedidoUseCases pedidoUseCases;
	    private RabbitConsumerPedidoQueueGateway rabbitConsumerPedidoQueueGateway;

	    @BeforeEach
	    public void setup() {
	        log = mock(Logger.class);
	        template = mock(RabbitTemplate.class);
	        pedidoUseCases = mock(PedidoUseCases.class);
	        rabbitConsumerPedidoQueueGateway = new RabbitConsumerPedidoQueueGateway(pedidoUseCases, template);
	    }

	    @Test
	    public void testPagamentoRecebido() throws PedidoNaoEncontradoException {
	        EventPagamentoAtualizado pagamentoAtualizado = new EventPagamentoAtualizado(1, StatusPagamento.PAGO);
	        Pedido pedido = new Pedido();
	        var pedidoRealizado = new PedidoRealizadoEvent(pedido);
	        when(pedidoUseCases.atualizaPedidoPagamento(StatusPagamento.PAGO, 1)).thenReturn(pedido);
	        doNothing().when(template).convertAndSend("pedido-exchange",
	                "pedido-para-pruducao-routing-key",pedidoRealizado);

	        rabbitConsumerPedidoQueueGateway.pagamentoRecebido(pagamentoAtualizado);

	        verify(pedidoUseCases).atualizaPedidoPagamento(StatusPagamento.PAGO, 1);
	        verify(template).convertAndSend("pedido-exchange",
	                "pedido-para-pruducao-routing-key", pedidoRealizado);
	    }

	    @Test
	    public void testAtualizacaoStatusProducao() throws PedidoNaoEncontradoException {
	        PedidoAtualizadoEvent atualizacao = new PedidoAtualizadoEvent(1, null);
	        doNothing().when(pedidoUseCases).atualizaPedidoStatus(1, null);

	        rabbitConsumerPedidoQueueGateway.atualizacaoStatusProducao(atualizacao);

	        verify(pedidoUseCases).atualizaPedidoStatus(1, null);
	    }
}
