package com.JBooks.J_books.controller;

import com.JBooks.J_books.DTO.DadosAtualizarLivro;
import com.JBooks.J_books.DTO.DadosCadastroLivros;
import com.JBooks.J_books.DTO.DadosListagemLivro;
import com.JBooks.J_books.DTO.LivroFilter;
import com.JBooks.J_books.Specification.LivroSpecification;
import com.JBooks.J_books.model.Livro;
import com.JBooks.J_books.repository.livroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private livroRepository repository;

    @CacheEvict(value = "livros", allEntries = true)
    @PostMapping
    @Transactional
    public ResponseEntity<Void> adicionarLivro(@RequestBody @Valid DadosCadastroLivros dados, UriComponentsBuilder uriBuilder) {
        Livro livro = new Livro(dados);
        repository.save(livro);

        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build(); // 201 Created com Location header
    }

    @Cacheable(value = "livros")
    @GetMapping
    public ResponseEntity<Page<DadosListagemLivro>> listarLivro( @ModelAttribute LivroFilter filter,
            @PageableDefault(size = 5, sort = {"nome"}) Pageable page) {
        var lista = repository.findAll(LivroSpecification.withFilters(filter), page).map(DadosListagemLivro::new);
        return ResponseEntity.ok(lista);
    }


    @CacheEvict(value = "livros", allEntries = true)
    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarLivro(@RequestBody @Valid DadosAtualizarLivro dados) {
        var livro = repository.getReferenceById(dados.id());
        livro.atualizarDados(dados);
        return ResponseEntity.ok().build(); // 200 OK sem corpo
    }

    @CacheEvict(value = "livros",allEntries = true)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
