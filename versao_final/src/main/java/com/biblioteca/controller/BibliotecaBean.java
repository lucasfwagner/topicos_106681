package com.biblioteca.bean;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Livro;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.BibliotecaService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import io.quarkus.security.identity.SecurityIdentity;

import java.util.List;

@Named
@RequestScoped
public class BibliotecaBean {

    @Inject
    BibliotecaService service;

    @Inject
    SecurityIdentity security;

    // ===================== NOVO =====================
    private Livro novoLivro = new Livro(); // para o formulário de cadastro

    public Livro getNovoLivro() {
        return novoLivro;
    }

    public void setNovoLivro(Livro novoLivro) {
        this.novoLivro = novoLivro;
    }

    @Transactional
    public String salvarLivro() {
        if (novoLivro.getAutor() != null) {
            service.salvarLivro(novoLivro);
            // resetar o objeto para novo cadastro
            novoLivro = new Livro();
            // redirecionar ou permanecer na mesma página
            return "/admin/cadastro.xhtml?faces-redirect=true";
        }
        return null; // permanece na página se não houver autor selecionado
    }
    // ===================== FIM NOVO =====================

    // Métodos existentes
    public List<Autor> getAutores() {
        return service.listarTodosAutores();
    }

    public List<Livro> getLivros() {
        return service.listarTodosLivros();
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return service.listarEmprestimosAtivos();
    }

    public long getTotalAutores() {
        return service.contarTotalAutores();
    }

    public long getTotalLivros() {
        return service.contarTotalLivros();
    }

    public long getLivrosDisponiveis() {
        return service.contarLivrosDisponiveis();
    }

    public long getEmprestimosAtivosCount() {
        return service.contarEmprestimosAtivos();
    }

    public boolean isAdmin() {
        return security.hasRole("ADMIN");
    }

    public boolean isUser() {
        return security.hasRole("USER");
    }

    public String getUsername() {
        return security.getPrincipal() != null ? security.getPrincipal().getName() : "Guest";
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        facesContext.getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

@Transactional
public void realizarEmprestimo(Long livroId) {
    Livro livro = service.listarTodosLivros().stream()
            .filter(l -> l.getId().equals(livroId))
            .findFirst()
            .orElse(null);

    if (livro != null && Boolean.TRUE.equals(livro.getDisponivel())) {
        Emprestimo emprestimo = new Emprestimo(
                getUsername(),
                getUsername() + "@example.com",
                livro
        );

        livro.setDisponivel(false);

        service.salvarEmprestimo(emprestimo);
        service.atualizarLivro(livro);
    }
}
 }
