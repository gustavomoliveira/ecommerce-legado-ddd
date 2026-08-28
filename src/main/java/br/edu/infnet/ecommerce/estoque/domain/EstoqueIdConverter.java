package br.edu.infnet.ecommerce.estoque.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstoqueIdConverter implements AttributeConverter<EstoqueId, Long> {
    @Override
    public Long convertToDatabaseColumn(EstoqueId id) {
        return id == null ? null : id.valor();
    }

    @Override
    public EstoqueId convertToEntityAttribute(Long valor) {
        return valor == null ? null : new EstoqueId(valor);
    }
}