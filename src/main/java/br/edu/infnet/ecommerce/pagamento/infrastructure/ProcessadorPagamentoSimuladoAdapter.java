package br.edu.infnet.ecommerce.pagamento.infrastructure;

import br.edu.infnet.ecommerce.pagamento.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProcessadorPagamentoSimuladoAdapter implements PortaProcessamentoPagamento {

    @Override
    public ResultadoProcessamento processar(
            Dinheiro valor,
            FormaPagamento formaPagamento,
            NumeroCartao numeroCartao
    ) {
        BigDecimal montante = valor.valor();

        if (montante == null || montante.compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoProcessamento.recusado("VALOR_INVALIDO");
        }

        if (formaPagamento != FormaPagamento.CARTAO) {
            return ResultadoProcessamento.recusado("FORMA_PAGAMENTO_NAO_SUPORTADA");
        }

        String numero = numeroCartao.valor();

        if (numero == null || numero.length() < 4) {
            return ResultadoProcessamento.recusado("CARTAO_INVALIDO");
        }

        if (montante.compareTo(new BigDecimal("10000.00")) > 0) {
            return ResultadoProcessamento.recusado("LIMITE_EXCEDIDO");
        }

        if (numero.endsWith("0000")) {
            return ResultadoProcessamento.recusado("CARTAO_BLOQUEADO");
        }

        return ResultadoProcessamento.aprovado(
                UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );
    }
}