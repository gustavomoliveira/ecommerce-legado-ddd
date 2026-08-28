package br.edu.infnet.ecommerce.usuario.domain;

public class EmailInvalidoException extends IllegalArgumentException {
    public EmailInvalidoException(String message) {
        super(message);
    }
}
