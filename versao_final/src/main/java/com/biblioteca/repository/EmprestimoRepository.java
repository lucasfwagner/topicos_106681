package com.biblioteca.repository;

import com.biblioteca.entity.Emprestimo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmprestimoRepository implements PanacheRepository<Emprestimo> {

    public List<Emprestimo> listarAtivos() {
        return list("dataDevolucao IS NULL");
    }
}
