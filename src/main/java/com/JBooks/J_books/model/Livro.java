package com.JBooks.J_books.model;

import com.JBooks.J_books.DTO.DadosAtualizarLivro;
import com.JBooks.J_books.DTO.DadosCadastroLivros;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



// Gera os construtores com e sem parâmetros.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "livro") // Transforma a classe em entidade JPA
@Table(name = "livros") // Define o nome da tabela no banco (opcional).
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id // Indica a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String autor;

    @NotBlank
    private String descricao;

    @NotBlank
    private String categoria;

    @Min(1)
    @Max(3)
    private Integer statusId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Livro(DadosCadastroLivros dados) {
        this.nome = dados.nome();
        this.autor = dados.autor();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.statusId = dados.statusId();

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
        if (dados.statusId() != null && dados.statusId() >= 1 && dados.statusId() <= 3) {
            this.statusId = dados.statusId();
        }
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
