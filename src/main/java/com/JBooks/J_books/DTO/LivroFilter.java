package com.JBooks.J_books.DTO;

public record LivroFilter(
        String nome,
        String autor,
        String categoria,
        Integer statusId
) {
}
