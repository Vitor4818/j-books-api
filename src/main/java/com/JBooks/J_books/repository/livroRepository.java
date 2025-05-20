package com.JBooks.J_books.repository;

import com.JBooks.J_books.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface livroRepository extends JpaRepository <Livro, Long>, JpaSpecificationExecutor<Livro> {
    List<Livro> findByUsuarioId(Long usuarioId);
    Optional<Livro> findByIdAndUsuarioId(Long id, Long usuarioId);

}
