package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.dto.BookDTO;
import org.example.dto.UserDTO;
import org.example.entity.Emprunt;
import org.example.repository.EmpruntRepository;
import org.example.user.BookServiceUser;
import org.example.user.UserServiceUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmpruntService {
    @Inject
    EmpruntRepository empruntRepository;

    @Inject
    @RestClient
    UserServiceUser userServiceUser;

    @Inject
    @RestClient
    BookServiceUser bookServiceUser;

    public List<Emprunt> getAllEmprunts() {
        List<Emprunt> emprunts = empruntRepository.listAll();
        return emprunts.stream()
                .map(this::enrichEmpruntWithDetails)
                .collect(Collectors.toList());
    }

    public Emprunt getEmpruntById(Long id) {
        return Optional.ofNullable(empruntRepository.findById(id))
                .map(this::enrichEmpruntWithDetails)
                .orElseThrow(() -> new WebApplicationException("Emprunt not found", 404));
    }

    @Transactional
    public Emprunt createEmprunt(Emprunt emprunt) {
        validateEmprunt(emprunt);
        emprunt.setDateEmprunt(LocalDateTime.now());
        emprunt.setReturnDate(LocalDateTime.now());
        empruntRepository.isPersistent(emprunt);
        return enrichEmpruntWithDetails(emprunt);
    }

    @Transactional
    public Emprunt updateEmprunt(Long id, Emprunt emprunt) {
        validateEmprunt(emprunt);

        return Optional.ofNullable(empruntRepository.findById(id))
                .map(existingEmprunt -> {
                    existingEmprunt.setQuantity(emprunt.getQuantity());
                    existingEmprunt.setDateEmprunt(emprunt.getDateEmprunt());
                    existingEmprunt.setReturnDate(emprunt.getReturnDate());
                    empruntRepository.persist(existingEmprunt);
                    return enrichEmpruntWithDetails(existingEmprunt);
                })
                .orElseThrow(() -> new WebApplicationException("Emprunt not found", 404));
    }

    @Transactional
    public void deleteEmprunt(Long id) {
        Optional.ofNullable(empruntRepository.findById(id))
                .ifPresentOrElse(empruntRepository::delete,
                        () -> { throw new WebApplicationException("Emprunt not found", 404); });
    }

    public List<Emprunt> getEmpruntsByUserId(Long userId) {
        if (userId == null) {
            throw new WebApplicationException("User ID is required", 400);
        }

        List<Emprunt> emprunts = empruntRepository.getEmpruntByIdUser(userId);

        if (emprunts.isEmpty()) {
            throw new WebApplicationException("No emprunts found for user ID " + userId, 404);
        }

        return emprunts.stream()
                .map(this::enrichEmpruntWithDetails)
                .collect(Collectors.toList());
    }


    private Emprunt enrichEmpruntWithDetails(Emprunt emprunt) {
        if (emprunt != null) {
            UserDTO user = userServiceUser.getUserById(emprunt.getUserId());
            BookDTO book = bookServiceUser.getBookById(emprunt.getBookId());

            if (user == null) {
                throw new WebApplicationException("User not found for ID " + emprunt.getUserId(), 404);
            }

            if (book == null) {
                throw new WebApplicationException("Book not found for ID " + emprunt.getBookId(), 404);
            }

            emprunt.setBookDTO(book);
            emprunt.setUserDTO(user);
        }
        return emprunt;
    }

    private void validateEmprunt(Emprunt emprunt) {
        if (emprunt == null) {
            throw new WebApplicationException("Emprunt cannot be null", 400);
        }

        if (emprunt.getUserId() == null) {
            throw new WebApplicationException("User ID cannot be null", 400);
        }

        if (emprunt.getBookId() == null) {
            throw new WebApplicationException("Book ID cannot be null", 400);
        }

        if (emprunt.getQuantity() <= 0) {
            throw new WebApplicationException("Quantity must be greater than 0", 400);
        }
}}
