package com.JBooks.J_books.Specification;

import com.JBooks.J_books.DTO.LivroFilter;
import com.JBooks.J_books.model.Livro;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LivroSpecification {
    public static Specification<Livro> withFilters(LivroFilter filter){
        return (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            if(filter.nome() != null){
                predicates.add(cb.like(
                        cb.lower(root.get("nome")), "%" +filter.nome().toLowerCase()+"%"));
            }
            if(filter.autor() != null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("autor")), "%" + filter.autor().toLowerCase()+"%"));
            }
            if(filter.categoria()!= null){
                predicates.add(cb.like(
                        cb.lower(root.get("categoria")), "%" +filter.categoria().toLowerCase()+"%"));
            }
            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
