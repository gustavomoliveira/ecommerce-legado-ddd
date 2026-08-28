package br.edu.infnet.ecommerce.pagamento.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PagamentoIdConverter implements AttributeConverter<PagamentoId, Long> {
    @Override
    public Long convertToDatabaseColumn(PagamentoId id) {
        return id == null ? null : id.valor();
    }

    @Override
    public PagamentoId convertToEntityAttribute(Long valor) {
        return valor == null ? null : new PagamentoId(valor);
    }
}
