package com.JBooks.J_books.controller;

import com.JBooks.J_books.DTO.DadosAtualizarLivro;
import com.JBooks.J_books.DTO.DadosCadastroLivros;
import com.JBooks.J_books.DTO.DadosListagemLivro;
import com.JBooks.J_books.DTO.LivroFilter;
import com.JBooks.J_books.Specification.LivroSpecification;
import com.JBooks.J_books.model.Livro;
import com.JBooks.J_books.model.Usuario;
import com.JBooks.J_books.repository.UsuarioRepository;
import com.JBooks.J_books.repository.livroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private livroRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Adiciona um novo livro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @CacheEvict(value = "livros", allEntries = true)
    @PostMapping
    @Transactional
    public ResponseEntity<Void> adicionarLivro(@RequestBody @Valid DadosCadastroLivros dados, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Livro livro = new Livro(dados);
        livro.setUsuario(usuario);
        repository.save(livro);
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Lista livros do usuário com filtros e paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/usuario/{id}")
    @Cacheable(value = "livros")
    public ResponseEntity<Page<DadosListagemLivro>> listarLivro(@ModelAttribute LivroFilter filter,
                                                                @PageableDefault(size = 3, sort = {"nome"}) Pageable page,
                                                                @PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        var lista = repository.findAll(LivroSpecification.withFilters(filter, id), page)
                .map(DadosListagemLivro::new);
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualiza dados de um livro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @CacheEvict(value = "livros", allEntries = true)
    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarLivro(@RequestBody @Valid DadosAtualizarLivro dados) {
        var livroOpt = repository.findById(dados.id());
        if (livroOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        Livro livro = livroOpt.get();
        livro.atualizarDados(dados);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleta um livro pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @CacheEvict(value = "livros", allEntries = true)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
