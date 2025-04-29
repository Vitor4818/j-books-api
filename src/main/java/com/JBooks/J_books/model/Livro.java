package com.JBooks.J_books.model;


import com.JBooks.J_books.DTO.DadosAtualizarLivro;
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

    public void atualizarDados(DadosAtualizarLivro dados) {
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.autor() != null && !dados.autor().isBlank()) {
            this.autor = dados.autor();
        }
        if (dados.descricao() != null && !dados.descricao().isBlank()) {
            this.descricao = dados.descricao();
        }
        if (dados.categoria() != null && !dados.categoria().isBlank()) {
            this.categoria = dados.categoria();
        }
    }


}
