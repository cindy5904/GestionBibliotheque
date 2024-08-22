package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Author;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {
}
