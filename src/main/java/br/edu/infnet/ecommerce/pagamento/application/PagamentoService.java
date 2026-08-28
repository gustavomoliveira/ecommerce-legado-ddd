package br.edu.infnet.ecommerce.pagamento.application;

import br.edu.infnet.ecommerce.pagamento.domain.*;
import br.edu.infnet.ecommerce.pagamento.domain.Pagamento;
import br.edu.infnet.ecommerce.pagamento.infrastructure.PagamentoRepository;
import br.edu.infnet.ecommerce.pedido.domain.PedidoId;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PortaProcessamentoPagamento portaProcessamentoPagamento;

    public PagamentoService(
            PagamentoRepository pagamentoRepository,
            PortaProcessamentoPagamento portaProcessamentoPagamento
    ) {
        this.pagamentoRepository = pagamentoRepository;
        this.portaProcessamentoPagamento = portaProcessamentoPagamento;
    }

    public Pagamento processar(
            PedidoId pedidoId,
            UsuarioId usuarioId,
            Dinheiro valor,
            FormaPagamento formaPagamento,
            NumeroCartao numeroCartao
    ) {
        ResultadoProcessamento resultado = portaProcessamentoPagamento.processar(
                valor,
                formaPagamento,
                numeroCartao
        );

        Pagamento pagamento = Pagamento.registrar(
                pedidoId,
                usuarioId,
                valor,
                formaPagamento,
                numeroCartao,
                resultado
        );

        return pagamentoRepository.save(pagamento);
    }
}