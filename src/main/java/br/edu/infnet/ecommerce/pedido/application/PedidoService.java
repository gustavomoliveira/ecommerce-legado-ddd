package br.edu.infnet.ecommerce.pedido.application;

import br.edu.infnet.ecommerce.pagamento.domain.PagamentoRecusadoException;
import br.edu.infnet.ecommerce.pedido.domain.*;
import br.edu.infnet.ecommerce.pedido.infrastructure.PedidoRepository;
import br.edu.infnet.ecommerce.pedido.infrastructure.request.CriarPedidoRequest;
import br.edu.infnet.ecommerce.pedido.infrastructure.request.ItemPedidoRequest;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import br.edu.infnet.ecommerce.exception.RecursoNaoEncontradoException;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ConsultaUsuarioPort consultaUsuarioPort;
    private final ConsultaProdutoPort consultaProdutoPort;
    private final GerenciamentoEstoquePort gerenciamentoEstoquePort;
    private final ProcessamentoPagamentoPort processamentoPagamentoPort;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ConsultaUsuarioPort consultaUsuarioPort,
            ConsultaProdutoPort consultaProdutoPort,
            GerenciamentoEstoquePort gerenciamentoEstoquePort,
            ProcessamentoPagamentoPort processamentoPagamentoPort
    ) {
        this.pedidoRepository = pedidoRepository;
        this.consultaUsuarioPort = consultaUsuarioPort;
        this.consultaProdutoPort = consultaProdutoPort;
        this.gerenciamentoEstoquePort = gerenciamentoEstoquePort;
        this.processamentoPagamentoPort = processamentoPagamentoPort;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(PedidoId id) {
        return pedidoRepository.findById(id.valor())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: " + id.valor()));
    }

    @Transactional
    public Pedido criar(CriarPedidoRequest request) {
        UsuarioId usuarioId = new UsuarioId(request.usuarioId());

        if (!consultaUsuarioPort.usuarioAtivo(usuarioId)) {
            throw new PedidoInvalidoException("Usuário inativo");
        }

        List<ItemPedido> itens = request.itens().stream()
                .map(this::montarItem)
                .toList();

        Pedido pedido = Pedido.criar(usuarioId, itens);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (ItemPedidoRequest itemRequest : request.itens()) {
            gerenciamentoEstoquePort.debitar(
                    new ProdutoId(itemRequest.produtoId()),
                    itemRequest.quantidade()
            );
        }

        ResultadoPagamento resultado = processamentoPagamentoPort.processar(
                pedidoSalvo.getId(),
                usuarioId,
                pedidoSalvo.getValorTotal(),
                request.formaPagamento(),
                request.numeroCartao()
        );

        if (!resultado.aprovado()) {
            pedidoSalvo.recusarPagamento();
            pedidoRepository.save(pedidoSalvo);
            throw new PagamentoRecusadoException("Pagamento recusado: " + resultado.motivo());
        }

        pedidoSalvo.confirmarPagamento();
        return pedidoRepository.save(pedidoSalvo);
    }

    private ItemPedido montarItem(ItemPedidoRequest itemRequest) {
        ProdutoId produtoId = new ProdutoId(itemRequest.produtoId());

        if (!consultaProdutoPort.produtoAtivo(produtoId)) {
            throw new PedidoInvalidoException("Produto inativo: " + produtoId.valor());
        }

        Dinheiro preco = consultaProdutoPort.precoDoProduto(produtoId);
        return new ItemPedido(produtoId, itemRequest.quantidade(), preco);
    }
}