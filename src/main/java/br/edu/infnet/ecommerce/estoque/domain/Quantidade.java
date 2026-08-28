package br.edu.infnet.ecommerce.estoque.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Quantidade(
        @Column(nullable = false)
        int valor
) {
    public Quantidade {
        if (valor < 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa");
        }
    }

    public boolean suficientePara(Quantidade solicitada) {
        return this.valor >= solicitada.valor;
    }

    public Quantidade subtrair(Quantidade outra) {
        return new Quantidade(this.valor - outra.valor);
    }
}