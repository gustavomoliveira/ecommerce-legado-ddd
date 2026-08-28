package br.edu.infnet.ecommerce.pagamento.domain;

import br.edu.infnet.ecommerce.pedido.domain.PedidoId;
import br.edu.infnet.ecommerce.pedido.domain.PedidoIdConverter;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioIdConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = PedidoIdConverter.class)
    @Column(name = "pedido_id", nullable = false, unique = true)
    private PedidoId pedidoId;

    @Convert(converter = UsuarioIdConverter.class)
    @Column(name = "usuario_id", nullable = false)
    private UsuarioId usuarioId;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "valor_pagamento", nullable = false, precision = 15, scale = 2))
    private Dinheiro valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "numero_cartao_mascarado"))
    private NumeroCartao numeroCartaoMascarado;

    @Embedded
    private DecisaoPagamento decisaoPagamento;

    @Column(nullable = false)
    private LocalDateTime processadoEm;

    protected Pagamento() {
    }

    private Pagamento(
            PedidoId pedidoId,
            UsuarioId usuarioId,
            Dinheiro valor,
            FormaPagamento formaPagamento,
            NumeroCartao numeroCartaoMascarado,
            DecisaoPagamento decisaoPagamento
    ) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.numeroCartaoMascarado = numeroCartaoMascarado;
        this.decisaoPagamento = decisaoPagamento;
        this.processadoEm = LocalDateTime.now();
    }

    public static Pagamento registrar(
            PedidoId pedidoId,
            UsuarioId usuarioId,
            Dinheiro valor,
            FormaPagamento formaPagamento,
            NumeroCartao numeroCartao,
            ResultadoProcessamento resultado
    ) {
        DecisaoPagamento decisao = resultado.aprovado()
                ? new DecisaoPagamento(StatusPagamento.APROVADO, null, resultado.codigoAutorizacao())
                : new DecisaoPagamento(StatusPagamento.RECUSADO, resultado.motivo(), null);

        NumeroCartao numeroCartaoMascarado = new NumeroCartao(numeroCartao.numeroCartaoMascarado());

        return new Pagamento(
                pedidoId,
                usuarioId,
                valor,
                formaPagamento,
                numeroCartaoMascarado,
                decisao
        );
    }

    public boolean foiAprovado() {
        return decisaoPagamento.status() == StatusPagamento.APROVADO;
    }

    public PagamentoId getId() {
        return id == null ? null : new PagamentoId(id);
    }

    public PedidoId getPedidoId() {
        return pedidoId;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public Dinheiro getValor() {
        return valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public NumeroCartao getNumeroCartaoMascarado() {
        return numeroCartaoMascarado;
    }

    public DecisaoPagamento getDecisaoPagamento() {
        return decisaoPagamento;
    }

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }
}