package com.JBooks.J_books.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarLivro(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String autor,
        @NotBlank
        String descricao,
        @NotBlank
        String categoria,
        @Max(3)
        @Min(1)
        @NotNull
        Integer statusId
) {

}
