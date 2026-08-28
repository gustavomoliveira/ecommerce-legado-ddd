package br.edu.infnet.ecommerce.estoque.application;

import br.edu.infnet.ecommerce.estoque.domain.*;
import br.edu.infnet.ecommerce.estoque.infrastructure.EstoqueRepository;
import br.edu.infnet.ecommerce.exception.RecursoNaoEncontradoException;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ConsultaProdutoPort consultaProdutoPort;

    public EstoqueService(
            EstoqueRepository estoqueRepository,
            ConsultaProdutoPort consultaProdutoPort
    ) {
        this.estoqueRepository = estoqueRepository;
        this.consultaProdutoPort = consultaProdutoPort;
    }

    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorProduto(ProdutoId produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estoque não encontrado para o produto: " + produtoId.valor()
                ));
    }

    @Transactional
    public Estoque definirQuantidade(ProdutoId produtoId, int quantidade) {
        if (!consultaProdutoPort.produtoExiste(produtoId)) {
            throw new RecursoNaoEncontradoException(
                    "Produto não encontrado: " + produtoId.valor()
            );
        }

        Quantidade novaQuantidade = new Quantidade(quantidade);

        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseGet(() -> Estoque.criar(produtoId, novaQuantidade));

        estoque.redefinirQuantidade(novaQuantidade);
        return estoqueRepository.save(estoque);
    }

    public Estoque debitar(ProdutoId produtoId, int quantidade) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estoque não encontrado para o produto: " + produtoId.valor()
                ));
        estoque.debitar(new Quantidade(quantidade));
        return estoqueRepository.save(estoque);
    }
}