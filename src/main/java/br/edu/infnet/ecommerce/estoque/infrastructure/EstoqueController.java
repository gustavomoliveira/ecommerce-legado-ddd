package br.edu.infnet.ecommerce.estoque.infrastructure;

import br.edu.infnet.ecommerce.estoque.application.EstoqueService;
import br.edu.infnet.ecommerce.estoque.domain.Estoque;
import br.edu.infnet.ecommerce.estoque.infrastructure.request.AjusteEstoqueRequest;
import br.edu.infnet.ecommerce.estoque.infrastructure.response.EstoqueResponse;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public List<EstoqueResponse> listar() {
        return estoqueService.listar().stream()
                .map(EstoqueResponse::de)
                .toList();
    }

    @GetMapping("/produto/{produtoId}")
    public EstoqueResponse buscarPorProduto(@PathVariable Long produtoId) {
        Estoque estoque = estoqueService.buscarPorProduto(new ProdutoId(produtoId));
        return EstoqueResponse.de(estoque);
    }

    @PutMapping("/produto/{produtoId}")
    public EstoqueResponse definirQuantidade(
            @PathVariable Long produtoId,
            @Valid @RequestBody AjusteEstoqueRequest request
    ) {
        Estoque estoque = estoqueService.definirQuantidade(new ProdutoId(produtoId), request.quantidade());
        return EstoqueResponse.de(estoque);
    }
}