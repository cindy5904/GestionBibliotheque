package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserService userService;
    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @POST
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") Long id, User user) {
        return userService.updateUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
    }
}
