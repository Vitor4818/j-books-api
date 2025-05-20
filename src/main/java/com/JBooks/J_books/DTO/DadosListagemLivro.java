package com.JBooks.J_books.DTO;

import com.JBooks.J_books.model.Livro;

public record DadosListagemLivro(
        Long id,
        String nome,
        String autor,
        String descricao,
        String categoria,
        int statusId,
        Integer usuarioId
) {
    public DadosListagemLivro(Livro livro) {
        this(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getDescricao(),
                livro.getCategoria(),
                livro.getStatusId(),
                livro.getUsuario() != null ? livro.getUsuario().getId() : null
        );
    }
}
