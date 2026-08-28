package br.edu.infnet.ecommerce.estoque.domain;

import br.edu.infnet.ecommerce.produto.domain.ProdutoId;

public interface ConsultaProdutoPort {
    boolean produtoExiste(ProdutoId produtoId);
}