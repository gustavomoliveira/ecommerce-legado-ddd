package br.edu.infnet.ecommerce.produto.infrastructure;

import br.edu.infnet.ecommerce.produto.application.ProdutoService;
import br.edu.infnet.ecommerce.produto.domain.Produto;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import br.edu.infnet.ecommerce.produto.infrastructure.request.AtualizarProdutoRequest;
import br.edu.infnet.ecommerce.produto.infrastructure.request.CriarProdutoRequest;
import br.edu.infnet.ecommerce.produto.infrastructure.response.ProdutoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return produtoService.listar().stream()
                .map(ProdutoResponse::de)
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id) {
        Produto produto = produtoService.buscar(new ProdutoId(id));
        return ProdutoResponse.de(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@Valid @RequestBody CriarProdutoRequest request) {
        Produto produto = produtoService.cadastrar(request);
        return ProdutoResponse.de(produto);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarProdutoRequest request
    ) {
        Produto produto = produtoService.atualizar(new ProdutoId(id), request);
        return ProdutoResponse.de(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(new ProdutoId(id));
    }
}