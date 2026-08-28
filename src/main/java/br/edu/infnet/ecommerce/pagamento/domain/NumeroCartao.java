package br.edu.infnet.ecommerce.pagamento.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record NumeroCartao(String valor) {

    public String numeroCartaoMascarado() {
        if (valor == null || valor.length() < 4) {
            return "****";
        }

        return "**** **** **** " + valor.substring(valor.length() - 4);
    }
}
