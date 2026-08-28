package br.edu.infnet.ecommerce.pedido.domain;

import br.edu.infnet.ecommerce.usuario.domain.UsuarioId;

public interface ConsultaUsuarioPort {
    boolean usuarioAtivo(UsuarioId usuarioId);
}