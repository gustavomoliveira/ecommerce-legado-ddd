package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.pagamento.application.PagamentoService;
import br.edu.infnet.ecommerce.pagamento.domain.FormaPagamento;
import br.edu.infnet.ecommerce.pagamento.domain.Pagamento;
import br.edu.infnet.ecommerce.pagamento.domain.NumeroCartao;
import br.edu.infnet.ecommerce.pedido.domain.PedidoId;
import br.edu.infnet.ecommerce.pedido.domain.ProcessamentoPagamentoPort;
import br.edu.infnet.ecommerce.pedido.domain.ResultadoPagamento;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import org.springframework.stereotype.Component;
import br.edu.infnet.ecommerce.pedido.domain.Dinheiro;

@Component
public class ProcessamentoPagamentoAdapter implements ProcessamentoPagamentoPort {

    private final PagamentoService pagamentoService;

    public ProcessamentoPagamentoAdapter(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Override
    public ResultadoPagamento processar(
            PedidoId pedidoId,
            UsuarioId usuarioId,
            Dinheiro valor,
            String formaPagamento,
            String numeroCartao
    ) {
        Pagamento pagamento = pagamentoService.processar(
                pedidoId,
                usuarioId,
                new br.edu.infnet.ecommerce.pagamento.domain.Dinheiro(valor.valor()),
                FormaPagamento.valueOf(formaPagamento),
                new NumeroCartao(numeroCartao)
        );

        return new ResultadoPagamento(
                pagamento.foiAprovado(),
                pagamento.getDecisaoPagamento().motivo()
        );
    }
}