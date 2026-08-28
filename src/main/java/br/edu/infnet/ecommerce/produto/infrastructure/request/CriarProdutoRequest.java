package br.edu.infnet.ecommerce.produto.infrastructure.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CriarProdutoRequest(
        @NotBlank String nome,
        String descricao,
        @NotNull BigDecimal preco,
        boolean ativo
) {
}