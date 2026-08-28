package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;

public interface ProcessamentoPagamentoPort {
    ResultadoPagamento processar(
            PedidoId pedidoId,
            UsuarioId usuarioId,
            Dinheiro valor,
            String formaPagamento,
            String numeroCartao
    );
}