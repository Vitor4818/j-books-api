package com.JBooks.J_books.categoria;

import com.JBooks.J_books.livro.Categoria;
import lombok.Data;

public record DadosCadastroCategorias(Long id, Categoria categoria, String descricao) {


}
