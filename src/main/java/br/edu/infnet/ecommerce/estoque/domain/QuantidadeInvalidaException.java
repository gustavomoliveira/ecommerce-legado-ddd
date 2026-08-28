package br.edu.infnet.ecommerce.estoque.domain;

public class QuantidadeInvalidaException extends IllegalArgumentException {
    public QuantidadeInvalidaException(String message) {
        super(message);
    }
}