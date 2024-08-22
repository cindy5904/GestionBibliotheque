package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
}
