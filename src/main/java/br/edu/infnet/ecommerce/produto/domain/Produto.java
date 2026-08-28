package br.edu.infnet.ecommerce.produto.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Embedded
    private Preco preco;

    private boolean ativo;

    protected Produto() {
    }

    private Produto(String nome, String descricao, Preco preco, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public static Produto cadastrar(String nome, String descricao, Preco preco, boolean ativo) {
        return new Produto(nome, descricao, preco, ativo);
    }

    public void atualizar(String nome, String descricao, Preco preco, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public ProdutoId getId() {
        return id == null ? null : new ProdutoId(id);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Preco getPreco() {
        return preco;
    }

    public boolean isAtivo() {
        return ativo;
    }
}