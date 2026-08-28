package br.edu.infnet.ecommerce.produto.infrastructure.response;

import br.edu.infnet.ecommerce.produto.domain.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean ativo
) {
    public static ProdutoResponse de(Produto produto) {
        return new ProdutoResponse(
                produto.getId().valor(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco().valor(),
                produto.isAtivo()
        );
    }
}