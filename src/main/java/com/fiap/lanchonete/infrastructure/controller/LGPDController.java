package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usecases.LGPDUseCases;
import com.fiap.lanchonete.domain.entity.Anonimizacao;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/lgpd")
public class LGPDController {
	
	LGPDUseCases usecase;
	
	@PostMapping
	@Operation(description = "This method creates an anomization request.")
	public ResponseEntity<String> RegistraPedido(@RequestBody Anonimizacao pedido){
		usecase.realizaPedido(pedido);
		return new ResponseEntity<> ("Seu pedido de anoinimização foi criado com sucesso", HttpStatus.CREATED);
	};
	
	@GetMapping
	@Operation(description = "This method creates an anomization request.")
	public ResponseEntity<List<Anonimizacao>> BuscaPedidos(){
		return new ResponseEntity<> (usecase.buscaPedidos(), HttpStatus.CREATED);
	};
	
	
	
}
