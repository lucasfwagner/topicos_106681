package com.biblioteca.repository;

import com.biblioteca.entity.Livro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LivroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // LISTAGEM
    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT l FROM Livro l LEFT JOIN FETCH l.autor", Livro.class)
                .getResultList();
    }

    // CONTAGEM
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l", Long.class).getSingleResult();
    }

    public Long countByDisponivel(boolean disponivel) {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l WHERE l.disponivel = :disponivel", Long.class)
                .setParameter("disponivel", disponivel)
                .getSingleResult();
    }

    // SALVAR
    @Transactional
    public void save(Livro livro) {
        entityManager.persist(livro);
    }

    // ATUALIZAR
    @Transactional
    public Livro update(Livro livro) {
        return entityManager.merge(livro);
    }

    // REMOVER
    @Transactional
    public void delete(Livro livro) {
        if (!entityManager.contains(livro)) {
            livro = entityManager.merge(livro);
        }
        entityManager.remove(livro);
    }
}
