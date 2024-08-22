package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.entity.Book;
import org.example.repository.BookRepository;

import java.util.List;

@ApplicationScoped
public class BookService {
    @Inject
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.listAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book createBook(Book book) {
        bookRepository.persist(book);
        return book;
    }
    @Transactional
    public Book updateBook(Long id, Book book) {
        Book entity = bookRepository.findById(id);
        if (entity != null) {
            entity.setTitle(book.getTitle());
            entity.setIsbn(book.getIsbn());
        }
        return entity;
    }
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
