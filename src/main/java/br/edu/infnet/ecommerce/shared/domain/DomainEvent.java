package br.edu.infnet.ecommerce.shared.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {
    UUID eventId();
    Long aggregateId();
    LocalDateTime ocorridoEm();
}
