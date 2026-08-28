package br.edu.infnet.ecommerce.estoque.infrastructure;

import br.edu.infnet.ecommerce.estoque.domain.ConsultaProdutoPort;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import br.edu.infnet.ecommerce.produto.infrastructure.ProdutoRepository;
import org.springframework.stereotype.Component;

@Component("consultaProdutoAdapterEstoque")
public class ConsultaProdutoAdapter implements ConsultaProdutoPort {

    private final ProdutoRepository produtoRepository;

    public ConsultaProdutoAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public boolean produtoExiste(ProdutoId produtoId) {
        return produtoRepository.existsById(produtoId.valor());
    }
}