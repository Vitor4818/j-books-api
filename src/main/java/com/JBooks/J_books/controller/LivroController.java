package com.JBooks.J_books.controller;


import com.JBooks.J_books.DTO.DadosCadastroLivros;
import com.JBooks.J_books.repository.livroRepository;
import com.JBooks.J_books.model.Livro;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros") //Dizendo qual o endpoint
public class LivroController {
    @Autowired
    private livroRepository repository;

    @PostMapping
    @Transactional
    public void adicionarLivro(@RequestBody @Valid DadosCadastroLivros dados){
        repository.save(new Livro(dados));
System.out.println("Dados adicionado! "+ dados);

    }

 //   @GetMapping("/{id}")
 //   public Optional<DadosCadastroLivros> listarlivros(@PathVariable Long id){
 //       var livros = repository.stream()
//                .filter(c -> c.id().equals(id))
 //               .findFirst();
  //      return livros;
        //return repository.stream().findFirst();
    }

