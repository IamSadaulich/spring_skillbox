package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks(){
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks(){
        List<BookEntity> bookEntities = bookService.getBooksData().subList(6, 12);
        return bookEntities;
    }

    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks(){
        List<BookEntity> bookEntities = bookService.getBooksData().subList(12, 18);
        return bookEntities;
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
