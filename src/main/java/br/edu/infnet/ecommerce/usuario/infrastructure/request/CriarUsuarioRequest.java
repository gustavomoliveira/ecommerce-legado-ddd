package br.edu.infnet.ecommerce.usuario.infrastructure.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        boolean ativo
) {
}