package br.edu.infnet.ecommerce.estoque.domain;

import br.edu.infnet.ecommerce.produto.domain.ProdutoId;
import br.edu.infnet.ecommerce.produto.domain.ProdutoIdConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "estoques")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ProdutoIdConverter.class)
    @Column(name = "produto_id", nullable = false, unique = true)
    private ProdutoId produtoId;

    @Embedded
    private Quantidade quantidade;

    protected Estoque() {
    }

    private Estoque(ProdutoId produtoId, Quantidade quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public static Estoque criar(ProdutoId produtoId, Quantidade quantidadeInicial) {
        return new Estoque(produtoId, quantidadeInicial);
    }

    public void debitar(Quantidade quantidadeSolicitada) {
        if (!quantidade.suficientePara(quantidadeSolicitada)) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para o produto: " + produtoId.valor()
            );
        }
        this.quantidade = quantidade.subtrair(quantidadeSolicitada);
    }

    public void redefinirQuantidade(Quantidade novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    public EstoqueId getId() {
        return id == null ? null : new EstoqueId(id);
    }

    public ProdutoId getProdutoId() {
        return produtoId;
    }

    public Quantidade getQuantidade() {
        return quantidade;
    }
}