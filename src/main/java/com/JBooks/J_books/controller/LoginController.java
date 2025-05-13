package com.JBooks.J_books.controller;

import com.JBooks.J_books.model.Token;
import com.JBooks.J_books.model.Usuario;
import com.JBooks.J_books.repository.UsuarioRepository;
import com.JBooks.J_books.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public Token login(@RequestBody Usuario loginData){
        Usuario usuario = usuarioRepository.findByEmail(loginData.getEmail())
                .orElseThrow(() -> new RuntimeException("Senha inválida!"));

        if (!passwordEncoder.matches(loginData.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        return tokenService.criarToken(usuario);
    }

    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }


}
