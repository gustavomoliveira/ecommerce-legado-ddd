package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record ItemPedido(
        @Column(name = "produto_id", nullable = false)
        ProdutoId produtoId,

        @Column(nullable = false)
        int quantidade,

        @Embedded
        Dinheiro precoUnitario
) {
    public Dinheiro subtotal() {
        return precoUnitario.multiplicar(quantidade);
    }
}