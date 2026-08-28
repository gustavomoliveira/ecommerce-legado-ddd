package br.edu.infnet.ecommerce.pedido.infrastructure.response;

import br.edu.infnet.ecommerce.pedido.domain.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        Long produtoId,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {
    public static ItemPedidoResponse de(ItemPedido item) {
        return new ItemPedidoResponse(
                item.produtoId().valor(),
                item.quantidade(),
                item.precoUnitario().valor(),
                item.subtotal().valor()
        );
    }
}