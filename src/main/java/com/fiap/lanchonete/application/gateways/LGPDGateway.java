package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Anonimizacao;

public interface LGPDGateway {
	Anonimizacao criaAnonimizacaoPedido(Anonimizacao pedido);
	List<Anonimizacao> buscaAllAnonimizacaoPedido();
}
