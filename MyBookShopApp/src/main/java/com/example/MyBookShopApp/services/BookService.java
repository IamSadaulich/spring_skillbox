package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Page<BookEntity> getPageOfRecentBooks(int offset, int limit, Date from, Date to) {
        return bookRepository.findAllByPubDateAfterAndPubDateBeforeOrderByPubDateDesc(from, to, PageRequest.of(offset, limit));
    }

    public Page<BookEntity> getPageOfRecentBooksFrom(int offset, int limit, Date from) {
        return bookRepository.findAllByPubDateAfterOrderByPubDateDesc(from, PageRequest.of(offset, limit));
    }

    public Page<BookEntity> getPageOfRecentBooksTo(int offset, int limit, Date to) {
        return bookRepository.findAllByPubDateBeforeOrderByPubDateDesc(to, PageRequest.of(offset, limit));
    }

    public Page<BookEntity> getPageOfRecommendedBooks(int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }
}
