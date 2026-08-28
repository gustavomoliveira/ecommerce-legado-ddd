package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.produto.domain.ProdutoId;

public interface GerenciamentoEstoquePort {
    void debitar(ProdutoId produtoId, int quantidade);
}