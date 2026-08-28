package br.edu.infnet.ecommerce.usuario.application;

import br.edu.infnet.ecommerce.exception.RecursoNaoEncontradoException;
import br.edu.infnet.ecommerce.usuario.domain.Email;
import br.edu.infnet.ecommerce.usuario.domain.Usuario;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import br.edu.infnet.ecommerce.usuario.infrastructure.UsuarioRepository;
import br.edu.infnet.ecommerce.usuario.infrastructure.request.AtualizarUsuarioRequest;
import br.edu.infnet.ecommerce.usuario.infrastructure.request.CriarUsuarioRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(UsuarioId id) {
        return usuarioRepository.findById(id.valor())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Usuário não encontrado: " + id.valor()
                ));
    }

    public Usuario cadastrar(CriarUsuarioRequest request) {
        Usuario usuario = Usuario.cadastrar(
                request.nome(),
                new Email(request.email()),
                request.ativo()
        );
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(UsuarioId id, AtualizarUsuarioRequest request) {
        Usuario usuario = buscar(id);
        usuario.atualizar(
                request.nome(),
                new Email(request.email()),
                request.ativo()
        );
        return usuarioRepository.save(usuario);
    }

    public void excluir(UsuarioId id) {
        usuarioRepository.delete(buscar(id));
    }
}