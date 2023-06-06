package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.DTO.BooksDto;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    BookService bookService;

    @Autowired
    public ApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/recommended")
    public BooksDto recommended(@RequestParam int offset, @RequestParam int limit) {

        return new BooksDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/books/recent")
    public BooksDto recent(@RequestParam int offset, @RequestParam int limit) {
        return new BooksDto(bookService.getBooksData().subList(0, 10));
    }

    @GetMapping("/books/popular")
    public BooksDto popular(@RequestParam int offset, @RequestParam int limit, @RequestParam String from, @RequestParam String to) {
        return new BooksDto(bookService.getBooksData().subList(0, 10));
    }
}
