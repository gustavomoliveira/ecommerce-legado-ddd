package br.edu.infnet.ecommerce.usuario.infrastructure.response;

import br.edu.infnet.ecommerce.usuario.domain.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        boolean ativo
) {
    public static UsuarioResponse de(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId().valor(),
                usuario.getNome(),
                usuario.getEmail().valor(),
                usuario.isAtivo()
        );
    }
}