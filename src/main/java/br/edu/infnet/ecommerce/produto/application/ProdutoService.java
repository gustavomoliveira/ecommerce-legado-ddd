package br.edu.infnet.ecommerce.produto.application;

import br.edu.infnet.ecommerce.exception.RecursoNaoEncontradoException;
import br.edu.infnet.ecommerce.produto.domain.Preco;
import br.edu.infnet.ecommerce.produto.domain.Produto;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import br.edu.infnet.ecommerce.produto.infrastructure.ProdutoRepository;
import br.edu.infnet.ecommerce.produto.infrastructure.request.AtualizarProdutoRequest;
import br.edu.infnet.ecommerce.produto.infrastructure.request.CriarProdutoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscar(ProdutoId id) {
        return produtoRepository.findById(id.valor())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto não encontrado: " + id.valor()
                ));
    }

    public Produto cadastrar(CriarProdutoRequest request) {
        Produto produto = Produto.cadastrar(
                request.nome(),
                request.descricao(),
                new Preco(request.preco()),
                request.ativo()
        );
        return produtoRepository.save(produto);
    }

    public Produto atualizar(ProdutoId id, AtualizarProdutoRequest request) {
        Produto produto = buscar(id);
        produto.atualizar(
                request.nome(),
                request.descricao(),
                new Preco(request.preco()),
                request.ativo()
        );
        return produtoRepository.save(produto);
    }

    public void excluir(ProdutoId id) {
        produtoRepository.delete(buscar(id));
    }
}