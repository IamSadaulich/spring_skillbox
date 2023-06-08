package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.DTO.BooksDto;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiController {

    private String DATE_FORMAT = "dd.MM.yyyy";
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
    public BooksDto recent(@RequestParam int offset, @RequestParam int limit, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        if (from.isEmpty() && to.isEmpty()) {
            return new BooksDto(bookService.getPageOfRecentBooksTo(offset, limit, new Date()).getContent());
        }
        if (!from.isEmpty() && !to.isEmpty()) {
            return new BooksDto(bookService.getPageOfRecentBooks(offset, limit, dateParse(from), dateParse(to)).getContent());
        }
        if (from.isEmpty()) {
            return new BooksDto(bookService.getPageOfRecentBooksTo(offset, limit, dateParse(to)).getContent());
        }
        return new BooksDto(bookService.getPageOfRecentBooksFrom(offset, limit, dateParse(from)).getContent());
    }

    @GetMapping("/books/popular")
    public BooksDto popular(@RequestParam int offset, @RequestParam int limit) {
        return new BooksDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    private Date dateParse(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
