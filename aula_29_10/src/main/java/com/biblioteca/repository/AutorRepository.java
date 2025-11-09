package com.biblioteca.repository;

import com.biblioteca.entity.Autor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AutorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // LISTAGEM
    public List<Autor> findAll() {
        return entityManager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    // CONTAGEM
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Autor a", Long.class).getSingleResult();
    }

    // SALVAR
    @Transactional
    public void save(Autor autor) {
        entityManager.persist(autor);
    }

    // ATUALIZAR
    @Transactional
    public Autor update(Autor autor) {
        return entityManager.merge(autor);
    }

    // REMOVER
    @Transactional
    public void delete(Autor autor) {
        if (!entityManager.contains(autor)) {
            autor = entityManager.merge(autor);
        }
        entityManager.remove(autor);
    }
}
