package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.pedido.domain.PedidoPagoEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoPagoListener {

    @KafkaListener(topics = "pedido-eventos", groupId = "notificacao-service")
    public void aoReceberPedidoPago(PedidoPagoEvent evento) {
        System.out.println(
                "Notificar usuário: " + evento.usuarioId().valor()
                + " sobre o pedido " + evento.pedidoId().valor()
                + " pago no valor de " + evento.valorTotal()
        );
    }
}
