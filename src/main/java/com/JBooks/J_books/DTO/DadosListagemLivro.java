package com.JBooks.J_books.DTO;

import com.JBooks.J_books.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosListagemLivro(Long id, String nome, String autor, String descricao, String categoria) {
    public DadosListagemLivro (Livro livro){
        this(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getDescricao(),
                livro.getCategoria());
    }
}
