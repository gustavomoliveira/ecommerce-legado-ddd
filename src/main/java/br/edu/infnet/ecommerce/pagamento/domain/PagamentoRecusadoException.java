package br.edu.infnet.ecommerce.pagamento.domain;

public class PagamentoRecusadoException extends RuntimeException {

    public PagamentoRecusadoException(String message) {
        super(message);
    }
}
