package com.JBooks.J_books.controller;


import com.JBooks.J_books.categoria.DadosCadastroCategorias;
import com.JBooks.J_books.livro.DadosCadastroLivros;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros") //Dizendo qual o endpoint
public class LivroController {
    private List<DadosCadastroLivros> repository = new ArrayList<>();


    @PostMapping
    public void adicionarLivro(@RequestBody DadosCadastroLivros dados){
        repository.add(dados);
        System.out.println("Dados adicionado! "+ dados);

    }

    @GetMapping("/{id}")
    public Optional<DadosCadastroLivros> listarlivros(@PathVariable Long id){
        var livros = repository.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return livros;
        //return repository.stream().findFirst();
    }
}
