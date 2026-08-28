package br.edu.infnet.ecommerce.estoque.infrastructure;

import br.edu.infnet.ecommerce.estoque.domain.Estoque;
import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProdutoId(ProdutoId produtoId);
}