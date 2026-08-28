package br.edu.infnet.ecommerce.config;

import br.edu.infnet.ecommerce.estoque.domain.Estoque;
import br.edu.infnet.ecommerce.estoque.domain.Quantidade;
import br.edu.infnet.ecommerce.estoque.infrastructure.EstoqueRepository;
import br.edu.infnet.ecommerce.produto.domain.Preco;
import br.edu.infnet.ecommerce.produto.domain.Produto;
import br.edu.infnet.ecommerce.produto.infrastructure.ProdutoRepository;
import br.edu.infnet.ecommerce.usuario.domain.Email;
import br.edu.infnet.ecommerce.usuario.domain.Usuario;
import br.edu.infnet.ecommerce.usuario.infrastructure.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DadosIniciaisConfig {

    @Bean
    CommandLineRunner carregarDados(
            UsuarioRepository usuarioRepository,
            ProdutoRepository produtoRepository,
            EstoqueRepository estoqueRepository
    ) {
        return args -> {
            usuarioRepository.save(
                    Usuario.cadastrar("Ana Souza", new Email("ana@exemplo.com"), true)
            );
            usuarioRepository.save(
                    Usuario.cadastrar("Bruno Lima", new Email("bruno@exemplo.com"), true)
            );
            usuarioRepository.save(
                    Usuario.cadastrar("Carlos Inativo", new Email("carlos@exemplo.com"), false)
            );

            Produto notebook = produtoRepository.save(
                    Produto.cadastrar(
                            "Notebook",
                            "Notebook para uso profissional",
                            new Preco(new BigDecimal("4500.00")),
                            true
                    )
            );

            Produto teclado = produtoRepository.save(
                    Produto.cadastrar(
                            "Teclado mecânico",
                            "Teclado ABNT2",
                            new Preco(new BigDecimal("350.00")),
                            true
                    )
            );

            Produto mouse = produtoRepository.save(
                    Produto.cadastrar(
                            "Mouse",
                            "Mouse sem fio",
                            new Preco(new BigDecimal("150.00")),
                            true
                    )
            );

            Produto servidor = produtoRepository.save(
                    Produto.cadastrar(
                            "Servidor",
                            "Servidor de alto desempenho",
                            new Preco(new BigDecimal("7500.00")),
                            true
                    )
            );

            Produto produtoInativo = produtoRepository.save(
                    Produto.cadastrar(
                            "Produto descontinuado",
                            "Produto não disponível",
                            new Preco(new BigDecimal("99.00")),
                            false
                    )
            );

            estoqueRepository.save(Estoque.criar(notebook.getId(), new Quantidade(10)));
            estoqueRepository.save(Estoque.criar(teclado.getId(), new Quantidade(25)));
            estoqueRepository.save(Estoque.criar(mouse.getId(), new Quantidade(50)));
            estoqueRepository.save(Estoque.criar(servidor.getId(), new Quantidade(5)));
            estoqueRepository.save(Estoque.criar(produtoInativo.getId(), new Quantidade(100)));
        };
    }
}