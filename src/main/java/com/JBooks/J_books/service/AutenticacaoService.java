package com.JBooks.J_books.service;

import com.JBooks.J_books.model.Usuario;
import com.JBooks.J_books.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));

                return User.builder()
                        .username(usuario.getEmail())
                        .password(usuario.getPassword())
                        .roles("USER")
                        .build();

    }

}
