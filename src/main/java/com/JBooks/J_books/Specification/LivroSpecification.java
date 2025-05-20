package com.JBooks.J_books.Specification;

import com.JBooks.J_books.DTO.LivroFilter;
import com.JBooks.J_books.model.Livro;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LivroSpecification {
    public static Specification<Livro> withFilters(LivroFilter filter, Long usuarioId){
        return (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por nome
            if(filter.nome() != null){
                predicates.add(cb.like(
                        cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            // Filtro por autor
            if(filter.autor() != null){
                predicates.add(cb.like(
                        cb.lower(root.get("autor")), "%" + filter.autor().toLowerCase() + "%"));
            }

            // Filtro por categoria
            if(filter.categoria() != null){
                predicates.add(cb.like(
                        cb.lower(root.get("categoria")), "%" + filter.categoria().toLowerCase() + "%"));
            }

            // Filtro por statusId
            if(filter.statusId() != null){
                predicates.add(cb.equal(root.get("statusId"), filter.statusId()));
            }
            
            // Filtro pelo usuário logado
            if (usuarioId != null) {
                predicates.add(cb.equal(root.get("usuario").get("id"), usuarioId));
            }

            // Combina todas as condições
            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };

    }
}
