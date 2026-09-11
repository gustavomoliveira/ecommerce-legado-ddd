package br.edu.infnet.ecommerce.pedido.application;

import br.edu.infnet.ecommerce.exception.RecursoNaoEncontradoException;
import br.edu.infnet.ecommerce.pedido.domain.Pedido;
import br.edu.infnet.ecommerce.pedido.infrastructure.PedidoRepository;
import br.edu.infnet.ecommerce.shared.domain.PublicadorDeEventosPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConfirmarPagamentoCommandHandler {

    private final PedidoRepository repository;
    private final PublicadorDeEventosPort publicadorDeEventosPort;

    public ConfirmarPagamentoCommandHandler(PedidoRepository repository, PublicadorDeEventosPort publicadorDeEventosPort) {
        this.repository = repository;
        this.publicadorDeEventosPort = publicadorDeEventosPort;
    }

    @Transactional
    public void handle(ConfirmarPagamentoCommand command) {
        Pedido pedido = repository.findById(command.pedidoId().valor())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido não encontrado: " + command.pedidoId().valor()
                ));

        pedido.confirmarPagamento();
        repository.save(pedido);

        publicadorDeEventosPort.publicar(pedido.getEventosPendentes());
        pedido.limparEventos();
    }
}
