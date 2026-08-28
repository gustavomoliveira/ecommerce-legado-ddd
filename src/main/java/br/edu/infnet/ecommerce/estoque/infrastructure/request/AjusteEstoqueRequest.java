package br.edu.infnet.ecommerce.estoque.infrastructure.request;

import jakarta.validation.constraints.NotNull;

public record AjusteEstoqueRequest(
        @NotNull Integer quantidade
) {
}