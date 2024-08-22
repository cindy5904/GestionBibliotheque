package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.Book;
import org.example.service.BookService;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    @Inject
    BookService bookService;

    @GET
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") Long id){
        return bookService.getBookById(id);
    }

    @POST
    public Book createBook(Book book) {
        return bookService.createBook(book);
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        return bookService.updateBook(id, book);
    }
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
    }
}
