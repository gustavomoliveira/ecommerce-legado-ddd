package br.edu.infnet.ecommerce.pedido.domain;

public class PedidoInvalidoException extends IllegalArgumentException {
    public PedidoInvalidoException(String message) {
        super(message);
    }
}