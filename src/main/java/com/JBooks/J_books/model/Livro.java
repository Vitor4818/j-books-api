package com.JBooks.J_books.model;


import com.JBooks.J_books.DTO.DadosCadastroLivros;
import jakarta.persistence.*;
import lombok.*;

//geram os construtores com e sem parâmetros.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity (name = "livro") //Transforma a classe em entidade JPA
@Table (name = "livros") //define o nome da tabela no banco (opcional).
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id //indicam a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private String descricao;
    private String categoria;

    public Livro(DadosCadastroLivros dados) {
        this.nome = dados.nome();
        this.autor = dados.autor();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
    }
}
