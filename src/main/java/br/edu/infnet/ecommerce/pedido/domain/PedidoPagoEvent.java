package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.shared.domain.DomainEvent;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoPagoEvent(
        UUID eventId,
        Long aggregateId,
        PedidoId pedidoId,
        UsuarioId usuarioId,
        Dinheiro valorTotal,
        LocalDateTime ocorridoEm
) implements DomainEvent {

    public static PedidoPagoEvent criar(PedidoId pedidoId, UsuarioId usuarioId, Dinheiro valorTotal) {
        return new PedidoPagoEvent(
                UUID.randomUUID(),
                pedidoId.valor(),
                pedidoId,
                usuarioId,
                valorTotal,
                LocalDateTime.now()
        );
    }
}
