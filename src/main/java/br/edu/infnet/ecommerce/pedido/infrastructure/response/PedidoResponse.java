package br.edu.infnet.ecommerce.pedido.infrastructure.response;

import br.edu.infnet.ecommerce.pedido.domain.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        Long usuarioId,
        List<ItemPedidoResponse> itens,
        BigDecimal valorTotal,
        String status,
        LocalDateTime criadoEm
) {
    public static PedidoResponse de(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId().valor(),
                pedido.getUsuarioId().valor(),
                pedido.getItens().stream().map(ItemPedidoResponse::de).toList(),
                pedido.getValorTotal().valor(),
                pedido.getStatus().name(),
                pedido.getCriadoEm()
        );
    }
}