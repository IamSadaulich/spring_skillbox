package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.struct.DTO.BookDto;
import com.example.MyBookShopApp.data.AuthorEntity;
import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.data.struct.book.links.Book2AuthorEntity;
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
        List<BookEntity> bookEntities = bookService.getBooksData().subList(0, 30);
        return bookEntities;
    }

    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks(){
        List<BookEntity> bookEntities = bookService.getBooksData().subList(30, 60);
        return bookEntities;
    }

    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks(){
        List<BookEntity> bookEntities = bookService.getBooksData().subList(60, 90);
        return bookEntities;
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
