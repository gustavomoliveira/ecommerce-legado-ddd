package br.edu.infnet.ecommerce.pedido.domain;

public class DinheiroInvalidoException extends IllegalArgumentException {
    public DinheiroInvalidoException(String message) {
        super(message);
    }
}
