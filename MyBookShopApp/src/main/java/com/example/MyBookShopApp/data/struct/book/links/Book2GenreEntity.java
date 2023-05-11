package com.example.MyBookShopApp.data.struct.book.links;

import com.example.MyBookShopApp.data.BookEntity;

import javax.persistence.*;

@Entity
@Table(name = "book2genre")
public class Book2GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookId;

    @Column(columnDefinition = "INT NOT NULL")
    private int genreId;
}
