package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/postponed")
public class PostponedController {
    BookService bookService;

    @Autowired
    public PostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<BookEntity> bookList() {
        return bookService.getBooksData().subList(0,3);
    }

    @GetMapping
    public String postponedPage() {
        return "postponed";
    }
}
