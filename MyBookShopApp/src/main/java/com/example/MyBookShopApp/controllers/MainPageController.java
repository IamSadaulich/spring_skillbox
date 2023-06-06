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
    public List<BookDto> recommendedBooks(){
        List<BookDto> bookDtoList = new ArrayList<>();
        List<BookEntity> bookEntities = bookService.getBooksData().subList(0, 30);
        for (BookEntity bookEntity : bookEntities) {
            BookDto bookDto = new BookDto();
            bookDto.setTitle(bookEntity.getTitle());
            bookDto.setOldPrice(bookEntity.getPrice());
            if (bookEntity.getDiscount() == 0) {
                bookDto.setNewPrice(bookDto.getOldPrice());
            } else {
                bookDto.setNewPrice(bookEntity.getDiscount() * bookDto.getOldPrice() / 100);
            }
            bookDto.setAuthors(getAuthorNames(bookEntity));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    public String getAuthorNames(BookEntity bookEntity) {
        Set<Book2AuthorEntity> book2AuthorEntities = bookEntity.getBook2AuthorEntities();
        Map<Integer, String> authorsNamesMap = new TreeMap<>();
        String authorsNames = "";
        Iterator<Book2AuthorEntity> iterator = book2AuthorEntities.iterator();
        while (iterator.hasNext()) {
            Book2AuthorEntity book2AuthorEntity = iterator.next();
            AuthorEntity author = book2AuthorEntity.getAuthor();
            authorsNamesMap.put(book2AuthorEntity.getSortIndex(), book2AuthorEntity.getAuthor().getName());
        }
        for (Map.Entry<Integer, String> entry : authorsNamesMap.entrySet()) {
            if (!authorsNames.isEmpty()) {
                authorsNames = authorsNames.concat(", ");
            }
            authorsNames = authorsNames.concat(entry.getValue());
        }
        return authorsNames;
    }
}
