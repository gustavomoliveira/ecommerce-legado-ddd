package br.edu.infnet.ecommerce.pagamento.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record DecisaoPagamento(
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        StatusPagamento status,
        String motivo,
        String codigoAutorizacao
) {
}
