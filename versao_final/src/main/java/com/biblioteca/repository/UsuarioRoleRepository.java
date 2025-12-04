package com.biblioteca.repository;

import com.biblioteca.entity.UsuarioRole;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRoleRepository implements PanacheRepository<UsuarioRole> {

}
