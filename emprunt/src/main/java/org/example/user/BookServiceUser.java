package org.example.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.BookDTO;

@Path("/books")
@RegisterRestClient(configKey = "book-service")
public interface BookServiceUser {
    @GET
    @Path("/{id}")
    BookDTO getBookById(@PathParam("id") Long id);
}
