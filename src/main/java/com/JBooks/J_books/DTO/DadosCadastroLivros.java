package com.JBooks.J_books.DTO;


import com.JBooks.J_books.model.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLivros(
        @NotBlank
        String nome,
        @NotBlank
        String autor,
        @NotBlank
        String descricao,
        @NotBlank
        String categoria,
        @NotNull
        @Min(1)
        @Max(3)
        int statusId,
        Long usuarioId
        ) {
}
