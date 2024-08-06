package com.fiap.lanchonete.infrastructure.gateway.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.fiap.lanchonete.domain.entity.Anonimizacao;
import com.fiap.lanchonete.infrastructure.persistence.entity.LgpdEntity;

public class LgpdEntityMapper {

	public LgpdEntity map(Anonimizacao pedido) {
		return new LgpdEntity(pedido.getNome(),pedido.getEndereco(),pedido.getTelefone());
	}

	public Anonimizacao map(LgpdEntity pedido) {
		return new Anonimizacao(pedido.getNome(),pedido.getEndereco(),pedido.getTelefone());

	}

	 public List<Anonimizacao> map(List<LgpdEntity> findAll) {
	        return findAll.stream()
	                .map(entity -> new Anonimizacao(entity.getNome(), entity.getEndereco(), entity.getTelefone()))
	                .collect(Collectors.toList());
	    }

}
