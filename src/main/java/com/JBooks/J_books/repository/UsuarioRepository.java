package com.JBooks.J_books.repository;

import com.JBooks.J_books.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional <Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
