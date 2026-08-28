package br.edu.infnet.ecommerce.usuario.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UsuarioIdConverter implements AttributeConverter<UsuarioId, Long> {
    @Override
    public Long convertToDatabaseColumn(UsuarioId id) {
        return id == null ? null : id.valor();
    }

    @Override
    public UsuarioId convertToEntityAttribute(Long valor) {
        return valor == null ? null : new UsuarioId(valor);
    }
}
