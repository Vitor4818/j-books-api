package com.JBooks.J_books.DTO;


import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLivros(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String autor,
        @NotBlank
        String descricao,
        @NotBlank
        String categoria) {
}
