package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Emprunt;

import java.util.List;

@ApplicationScoped
public class EmpruntRepository implements PanacheRepository<Emprunt> {
    public List<Emprunt> getEmpruntByIdUser(Long userId) {
        return find("userId", userId).list();
    }
}
