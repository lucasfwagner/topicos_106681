package com.biblioteca.service;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.AutorRepository;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LivroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BibliotecaService {

    @Inject
    AutorRepository autorRepository;

    @Inject
    LivroRepository livroRepository;

    @Inject
    EmprestimoRepository emprestimoRepository;

    public List<Autor> listarTodosAutores() {
        return autorRepository.listAll();
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.listAll();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.listarAtivos();
    }


    public long contarTotalAutores() {
        return autorRepository.count();
    }

    public long contarTotalLivros() {
        return livroRepository.count();
    }

    public long contarLivrosDisponiveis() {
        return livroRepository.count("disponivel", true);
    }

    public long contarEmprestimosAtivos() {
        return emprestimoRepository.listarAtivos().size();
    }


    @Transactional
    public void salvarAutor(Autor autor) {
        autorRepository.persist(autor);
    }

    @Transactional
    public Autor atualizarAutor(Autor autor) {
        return autorRepository.getEntityManager().merge(autor);
    }

    @Transactional
    public void removerAutor(Autor autor) {
        autorRepository.delete(autor);
    }


    @Transactional
    public void salvarLivro(Livro livro) {
        livroRepository.persist(livro);
    }

    @Transactional
    public Livro atualizarLivro(Livro livro) {
        return livroRepository.getEntityManager().merge(livro);
    }

    @Transactional
    public void removerLivro(Livro livro) {
        livroRepository.delete(livro);
    }


    @Transactional
    public void salvarEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.persist(emprestimo);
    }

    @Transactional
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.getEntityManager().merge(emprestimo);
    }

    @Transactional
    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.delete(emprestimo);
    }
}
