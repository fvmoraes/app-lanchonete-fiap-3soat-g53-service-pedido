package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Anonimizacao;

public interface LGPDUseCases {
	public List<Anonimizacao> buscaPedidos();
	public Anonimizacao realizaPedido(Anonimizacao pedido);

}
