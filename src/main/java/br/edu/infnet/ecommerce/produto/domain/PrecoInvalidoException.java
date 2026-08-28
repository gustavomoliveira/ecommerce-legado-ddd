package br.edu.infnet.ecommerce.produto.domain;

public class PrecoInvalidoException extends IllegalArgumentException {
    public PrecoInvalidoException(String message) {
        super(message);
    }
}
