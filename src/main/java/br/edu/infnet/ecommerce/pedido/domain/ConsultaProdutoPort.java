package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.produto.domain.ProdutoId;

public interface ConsultaProdutoPort {
    boolean produtoAtivo(ProdutoId produtoId);
    Dinheiro precoDoProduto(ProdutoId produtoId);
}