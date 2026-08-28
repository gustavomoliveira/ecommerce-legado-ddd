package br.edu.infnet.ecommerce.usuario.infrastructure;

import br.edu.infnet.ecommerce.usuario.application.UsuarioService;
import br.edu.infnet.ecommerce.usuario.domain.Usuario;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import br.edu.infnet.ecommerce.usuario.infrastructure.request.AtualizarUsuarioRequest;
import br.edu.infnet.ecommerce.usuario.infrastructure.request.CriarUsuarioRequest;
import br.edu.infnet.ecommerce.usuario.infrastructure.response.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.listar().stream()
                .map(UsuarioResponse::de)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscar(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscar(new UsuarioId(id));
        return UsuarioResponse.de(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse criar(@Valid @RequestBody CriarUsuarioRequest request) {
        Usuario usuario = usuarioService.cadastrar(request);
        return UsuarioResponse.de(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarUsuarioRequest request
    ) {
        Usuario usuario = usuarioService.atualizar(new UsuarioId(id), request);
        return UsuarioResponse.de(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(new UsuarioId(id));
    }
}