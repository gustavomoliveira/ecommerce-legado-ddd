package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.estoque.application.EstoqueService;
import br.edu.infnet.ecommerce.pedido.domain.GerenciamentoEstoquePort;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import org.springframework.stereotype.Component;

@Component
public class GerenciamentoEstoqueAdapter implements GerenciamentoEstoquePort {

    private final EstoqueService estoqueService;

    public GerenciamentoEstoqueAdapter(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @Override
    public void debitar(ProdutoId produtoId, int quantidade) {
        estoqueService.debitar(produtoId, quantidade);
    }
}