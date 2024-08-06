package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.LGPDGateway;
import com.fiap.lanchonete.domain.entity.Anonimizacao;

public class LGPDUseCasesImp implements LGPDUseCases {

	private final LGPDGateway lgpdGateway;

	public LGPDUseCasesImp(LGPDGateway lgpdGateway) {
		this.lgpdGateway = lgpdGateway;

	}

	@Override
	public List<Anonimizacao> buscaPedidos() {
		return lgpdGateway.buscaAllAnonimizacaoPedido();
	}

	@Override
	public Anonimizacao realizaPedido(Anonimizacao pedido) {
		return lgpdGateway.criaAnonimizacaoPedido(pedido);
	}

}
