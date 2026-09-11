package br.edu.infnet.ecommerce.shared.domain;

import java.util.List;

public interface PublicadorDeEventosPort {
    void publicar(List<DomainEvent> eventos);
}
