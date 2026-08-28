package br.edu.infnet.ecommerce.pedido.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Dinheiro(
        @Column(nullable = false, precision = 15, scale = 2)
        BigDecimal valor
) {
    public static final Dinheiro ZERO = new Dinheiro(BigDecimal.ZERO);

    public Dinheiro {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new DinheiroInvalidoException("O valor não pode ser negativo");
        }
    }

    public Dinheiro somar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.valor));
    }

    public Dinheiro multiplicar(int quantidade) {
        return new Dinheiro(this.valor.multiply(BigDecimal.valueOf(quantidade)));
    }
}