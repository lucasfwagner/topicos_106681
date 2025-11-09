package com.biblioteca.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import com.biblioteca.service.BibliotecaService;
import com.biblioteca.entity.Livro;

@Path("/api/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BibliotecaResource {

    @Inject
    BibliotecaService bibliotecaService;

    @GET
    public Response listarTodos() {
        List<Livro> livros = bibliotecaService.listarTodosLivros();
        return Response.ok(livros).build();
    }

    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Livro livro = bibliotecaService.buscarLivroPorId(id);
        if (livro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(livro).build();
    }
}
