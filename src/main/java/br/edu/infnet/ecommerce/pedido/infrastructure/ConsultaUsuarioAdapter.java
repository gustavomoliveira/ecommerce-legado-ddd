package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.pedido.domain.ConsultaUsuarioPort;
import br.edu.infnet.ecommerce.usuario.application.UsuarioService;
import br.edu.infnet.ecommerce.usuario.domain.Usuario;
import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;
import org.springframework.stereotype.Component;

@Component
public class ConsultaUsuarioAdapter implements ConsultaUsuarioPort {

    private final UsuarioService usuarioService;

    public ConsultaUsuarioAdapter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean usuarioAtivo(UsuarioId usuarioId) {
        Usuario usuario = usuarioService.buscar(usuarioId);
        return usuario.isAtivo();
    }
}