package com.JBooks.J_books.controller;

import com.JBooks.J_books.DTO.UserDTO;
import com.JBooks.J_books.model.Token;
import com.JBooks.J_books.model.Usuario;
import com.JBooks.J_books.repository.UsuarioRepository;
import com.JBooks.J_books.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Registra um novo usuário e retorna token de autenticação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado e token retornado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/registrar")
    public ResponseEntity<Token> registrar(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário com esse email já existe");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        Token token = tokenService.criarToken(usuarioSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @Operation(summary = "Busca usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado e retornado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> listarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            UserDTO userDTO = new UserDTO(usuarioOpt.get());
            return ResponseEntity.ok(userDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    @Operation(summary = "Deleta usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para exclusão")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
