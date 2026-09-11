package br.edu.infnet.ecommerce.pedido.application;

import br.edu.infnet.ecommerce.pedido.domain.PedidoId;

public record ConfirmarPagamentoCommand(
        PedidoId pedidoId
) {
}
