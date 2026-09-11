package br.edu.infnet.ecommerce.shared.infrastructure;

import br.edu.infnet.ecommerce.shared.domain.DomainEvent;
import br.edu.infnet.ecommerce.shared.domain.PublicadorDeEventosPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicadorDeEventosKafkaAdapter implements PublicadorDeEventosPort {

    private static final String TOPICO = "pedido-eventos";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PublicadorDeEventosKafkaAdapter(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(List<DomainEvent> eventos) {
        eventos.forEach(e ->
                kafkaTemplate.send(TOPICO, e.aggregateId().toString(), e));
    }
}
