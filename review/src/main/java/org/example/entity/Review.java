package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.UserDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentaire;
    private int note;
    @Column(name= "book_id")
    private Long bookId;
    @Column(name= "user_id")
    private Long userId;

    @Transient
    public BookDto bookDto;

    @Transient
    public UserDto userDto;
}
