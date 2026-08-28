package br.edu.infnet.ecommerce.pedido.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PedidoIdConverter implements AttributeConverter<PedidoId, Long> {
    @Override
    public Long convertToDatabaseColumn(PedidoId id) {
        return id == null ? null : id.valor();
    }

    @Override
    public PedidoId convertToEntityAttribute(Long valor) {
        return valor == null ? null : new PedidoId(valor);
    }
}
