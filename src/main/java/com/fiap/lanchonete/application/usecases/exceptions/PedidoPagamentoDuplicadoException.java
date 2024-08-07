package com.fiap.lanchonete.application.usecases.exceptions;

public class PedidoPagamentoDuplicadoException extends Exception {
	private static final long serialVersionUID = 1L;

	public PedidoPagamentoDuplicadoException() {
        super("Pedido Finalizado com pagamento ja realizado");
    }
}