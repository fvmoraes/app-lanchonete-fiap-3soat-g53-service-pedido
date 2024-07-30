package com.fiap.lanchonete.infrastructure.gateway;

import java.util.List;

import com.fiap.lanchonete.application.gateways.LGPDGateway;
import com.fiap.lanchonete.domain.entity.Anonimizacao;
import com.fiap.lanchonete.infrastructure.gateway.mapper.LgpdEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.LgpdRepository;

public class LGPDRepositoryGateway implements LGPDGateway{

	private final LgpdRepository repository;
	private final LgpdEntityMapper mapper;

	public LGPDRepositoryGateway(LgpdRepository repository, LgpdEntityMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Anonimizacao criaAnonimizacaoPedido(Anonimizacao pedido) {
		var created = repository.save(mapper.map(pedido));
		return mapper.map(created);
	}

	@Override
	public List<Anonimizacao> buscaAllAnonimizacaoPedido() {
		return mapper.map(repository.findAll());
	}


}
