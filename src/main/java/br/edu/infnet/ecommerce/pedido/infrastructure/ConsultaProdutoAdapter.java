package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.pedido.domain.ConsultaProdutoPort;
import br.edu.infnet.ecommerce.pedido.domain.Dinheiro;
import br.edu.infnet.ecommerce.produto.application.ProdutoService;
import br.edu.infnet.ecommerce.produto.domain.Produto;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import org.springframework.stereotype.Component;

@Component("consultaProdutoAdapterPedido")
public class ConsultaProdutoAdapter implements ConsultaProdutoPort {

    private final ProdutoService produtoService;

    public ConsultaProdutoAdapter(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public boolean produtoAtivo(ProdutoId produtoId) {
        Produto produto = produtoService.buscar(produtoId);
        return produto.isAtivo();
    }

    @Override
    public Dinheiro precoDoProduto(ProdutoId produtoId) {
        Produto produto = produtoService.buscar(produtoId);
        return new Dinheiro(produto.getPreco().valor());
    }
}