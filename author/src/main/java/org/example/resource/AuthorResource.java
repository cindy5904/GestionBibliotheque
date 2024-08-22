package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.Author;
import org.example.service.AuthorService;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    @Inject
    AuthorService authorService;

    @GET
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") Long id){
        return authorService.getAuthorById(id);
    }

    @POST
    public Author createAuthor(Author author) {
        return authorService.createAuthor(author);
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") Long id, Author author) {
        return authorService.updateAuthor(id, author);
    }
    @DELETE
    @Path("/{id}")
    public void deleteAuthor(@PathParam("id") Long id) {
        authorService.deleteAuthor(id);
    }

}
