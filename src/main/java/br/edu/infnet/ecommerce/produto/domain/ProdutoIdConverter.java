package br.edu.infnet.ecommerce.produto.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProdutoIdConverter implements AttributeConverter<ProdutoId, Long> {
    @Override
    public Long convertToDatabaseColumn(ProdutoId id) {
        return id == null ? null : id.valor();
    }

    @Override
    public ProdutoId convertToEntityAttribute(Long valor) {
        return valor == null ? null : new ProdutoId(valor);
    }
}