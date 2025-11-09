package com.biblioteca.service;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Livro;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.repository.AutorRepository;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.EmprestimoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BibliotecaService {

    @Inject
    private AutorRepository autorRepository;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private EmprestimoRepository emprestimoRepository;

    // ----------------------------
    // LISTAGEM
    // ----------------------------
    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.findAtivos();
    }

    // ----------------------------
    // ESTATÍSTICAS
    // ----------------------------
    public long contarTotalAutores() {
        return autorRepository.count();
    }

    public long contarTotalLivros() {
        return livroRepository.count();
    }

    public long contarLivrosDisponiveis() {
        return livroRepository.countByDisponivel(true);
    }

    public long contarEmprestimosAtivos() {
        return emprestimoRepository.countAtivos();
    }

    // ----------------------------
    // CRUD AUTORES
    // ----------------------------
    @Transactional
    public void cadastrarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    @Transactional
    public void atualizarAutor(Autor autor) {
        autorRepository.update(autor);
    }

    @Transactional
    public void removerAutor(Autor autor) {
        autorRepository.delete(autor);
    }

    // ----------------------------
    // CRUD LIVROS
    // ----------------------------
    @Transactional
    public void cadastrarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    @Transactional
    public void atualizarLivro(Livro livro) {
        livroRepository.update(livro);
    }

    @Transactional
    public void removerLivro(Livro livro) {
        livroRepository.delete(livro);
    }

    // ----------------------------
    // CRUD EMPRESTIMOS
    // ----------------------------
    @Transactional
    public void cadastrarEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void atualizarEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.update(emprestimo);
    }

    @Transactional
    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.delete(emprestimo);
    }
}
