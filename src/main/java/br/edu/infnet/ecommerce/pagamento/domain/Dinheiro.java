package br.edu.infnet.ecommerce.pagamento.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Dinheiro(
        @Column(nullable = false, precision = 15, scale = 2)
        BigDecimal valor
) {
}
