package com.biblioteca.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_roles")
public class UsuarioRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UsuarioRole() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
