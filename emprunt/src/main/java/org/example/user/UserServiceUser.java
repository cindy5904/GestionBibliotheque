package org.example.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.UserDTO;

@Path("/users")
@RegisterRestClient(configKey = "user-service")
public interface UserServiceUser {
    @GET
    @Path("/{id}")
    UserDTO getUserById(@PathParam("id") Long id);
}
