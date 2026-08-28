package br.edu.infnet.ecommerce.estoque.infrastructure.response;

import br.edu.infnet.ecommerce.estoque.domain.Estoque;

public record EstoqueResponse(
        Long id,
        Long produtoId,
        int quantidade
) {
    public static EstoqueResponse de(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId().valor(),
                estoque.getProdutoId().valor(),
                estoque.getQuantidade().valor()
        );
    }
}