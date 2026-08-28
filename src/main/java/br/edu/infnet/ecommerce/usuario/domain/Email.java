package br.edu.infnet.ecommerce.usuario.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Email(
        @Column(name = "email", nullable = false, unique = true)
        String valor
) {
    public Email {
        if (valor == null || !valor.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new EmailInvalidoException("E-mail inválido: " + valor);
        }
    }
}