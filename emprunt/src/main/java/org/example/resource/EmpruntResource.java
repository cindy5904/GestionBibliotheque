package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.Emprunt;
import org.example.service.EmpruntService;

import java.util.List;

@Path("/emprunts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpruntResource {
    @Inject
    EmpruntService empruntService;

    @GET
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @GET
    @Path("/{id}")
    public Emprunt getEmpruntById(@PathParam("id") Long id) {
        return empruntService.getEmpruntById(id);
    }

    @POST
    public Emprunt createEmprunt(Emprunt emprunt) {
        return empruntService.createEmprunt(emprunt);
    }

    @PUT
    @Path("/{id}")
    public Emprunt updateEmprunt(@PathParam("id") Long id, Emprunt emprunt) {
        return empruntService.updateEmprunt(id, emprunt);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEmprunt(@PathParam("id") Long id) {
        empruntService.deleteEmprunt(id);
    }


    @GET
    @Path("/user/{userId}")
    public List<Emprunt> getEmpruntsByUserId(@PathParam("userId") Long userId) {
        return empruntService.getEmpruntsByUserId(userId);
    }
}
