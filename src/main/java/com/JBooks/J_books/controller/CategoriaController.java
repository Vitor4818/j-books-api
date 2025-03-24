package com.JBooks.J_books.controller;


import com.JBooks.J_books.categoria.DadosCadastroCategorias;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private List<DadosCadastroCategorias> repository = new ArrayList<>();


    @PostMapping
    public void adicionarCategoria(@RequestBody DadosCadastroCategorias dados) {
        repository.add(dados);
        System.out.println("Dados adicionados!\n"+dados);
    }


    @GetMapping ("/{id}")
    public Optional<DadosCadastroCategorias> listarCategorias(@PathVariable Long id){
        var categorias = repository.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return categorias;
        //return repository.stream().findFirst();
    }

}
