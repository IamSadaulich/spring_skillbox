package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> getPageOfRecommendedBooks(int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }
}
