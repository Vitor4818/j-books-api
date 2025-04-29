package com.JBooks.J_books.repository;

import com.JBooks.J_books.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface livroRepository extends JpaRepository <Livro, Long>, JpaSpecificationExecutor<Livro> {

}
