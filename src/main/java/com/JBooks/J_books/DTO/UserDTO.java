package com.JBooks.J_books.DTO;

import com.JBooks.J_books.model.Usuario;

public record UserDTO(Integer id,String nome, String email) {

    public UserDTO(Usuario usuario){
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail()
        );
    }
}
