package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioIdConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = UsuarioIdConverter.class)
    @Column(name = "usuario_id", nullable = false)
    private UsuarioId usuarioId;

    @ElementCollection
    @CollectionTable(name = "itens_pedido", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<ItemPedido> itens;

    @Embedded
    private Dinheiro valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    protected Pedido() {
    }

    private Pedido(UsuarioId usuarioId, List<ItemPedido> itens) {
        this.usuarioId = usuarioId;
        this.itens = itens;
        this.valorTotal = calcularTotal(itens);
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.criadoEm = LocalDateTime.now();
    }

    public static Pedido criar(UsuarioId usuarioId, List<ItemPedido> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new PedidoInvalidoException("O pedido precisa ter ao menos um item");
        }
        return new Pedido(usuarioId, itens);
    }

    private static Dinheiro calcularTotal(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedido::subtotal)
                .reduce(Dinheiro.ZERO, Dinheiro::somar);
    }

    public void confirmarPagamento() {
        this.status = StatusPedido.PAGO;
    }

    public void recusarPagamento() {
        this.status = StatusPedido.PAGAMENTO_RECUSADO;
    }

    public PedidoId getId() {
        return id == null ? null : new PedidoId(id);
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Dinheiro getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}