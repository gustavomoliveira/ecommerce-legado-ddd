package br.edu.infnet.ecommerce.produto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Preco(
        @Column(nullable = false, precision = 15, scale = 2)
        BigDecimal valor
) {
    public Preco {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoInvalidoException("O preço deve ser maior que zero");
        }
    }
}