package com.fiap.lanchonete.application.usecases.exceptions;

public class PedidoComProdutoNaoCadastradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public PedidoComProdutoNaoCadastradoException() {
        super("Pedido com produto nao cadastrado");
    }
}
