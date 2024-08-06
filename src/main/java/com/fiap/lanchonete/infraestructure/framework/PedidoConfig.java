package com.fiap.lanchonete.infraestructure.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.gateways.ProdutoGateway;
import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.PedidoUseCasesImp;
import com.fiap.lanchonete.infrastructure.gateway.PedidoRepositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;

@Configuration
public class PedidoConfig {

	@Bean
	PedidoUseCases pedidoInteractorBean(PedidoGateway PedidoGateway, ProdutoGateway produtoGateway) {
		return new PedidoUseCasesImp(PedidoGateway, produtoGateway);
	}

	@Bean
	PedidoGateway pedidoGateway(PedidoEntityMapper mapper, PedidoRepository repository) {
		return new PedidoRepositoryGateway(repository, mapper);
	}

	@Bean
	PedidoEntityMapper pedidoMapper() {
		return new PedidoEntityMapper();
	}

	@Bean
	PedidoRequestMapper pedidoRequestMapper() {
		return new PedidoRequestMapper();
	}
}
