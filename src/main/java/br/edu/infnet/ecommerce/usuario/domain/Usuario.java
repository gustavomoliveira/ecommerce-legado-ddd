package br.edu.infnet.ecommerce.usuario.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Email email;

    private boolean ativo;

    protected Usuario() {
    }

    private Usuario(String nome, Email email, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }

    public static Usuario cadastrar(String nome, Email email, boolean ativo) {
        return new Usuario(nome, email, ativo);
    }

    public void atualizar(String nome, Email email, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }

    public UsuarioId getId() {
        return id == null ? null : new UsuarioId(id);
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }
}