package org.example.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;

import java.util.List;

public class AuthorService {
    @Inject
    AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.listAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public Author createAuthor(Author author) {
        authorRepository.persist(author);
        return author;
    }
    @Transactional
    public Author updateAuthor(Long id, Author author) {
        Author entity = authorRepository.findById(id);
        if (entity != null) {
            entity.setName(author.getName());
            entity.setBiography(author.getBiography());
            entity.setDateOfBirth(author.getDateOfBirth());
        }
        return entity;
    }
    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
