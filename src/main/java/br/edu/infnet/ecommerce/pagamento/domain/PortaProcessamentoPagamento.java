package br.edu.infnet.ecommerce.pagamento.domain;

public interface PortaProcessamentoPagamento {
    ResultadoProcessamento processar(
            Dinheiro valor,
            FormaPagamento formaPagamento,
            NumeroCartao numeroCartao
    );
}