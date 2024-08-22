package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.BookDTO;
import org.example.dto.UserDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Emprunts")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private LocalDateTime dateEmprunt;
    private LocalDateTime returnDate;
    @Column(name= "book_id")
    private Long bookId;
    @Column(name= "user_id")
    private Long userId;

    @Transient
    public BookDTO bookDTO;

    @Transient
    public UserDTO userDTO;

}
