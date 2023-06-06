package com.example.MyBookShopApp.data.DTO;

import com.example.MyBookShopApp.data.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BooksDto {
    private int count;
    private List<BookDto> books;

    public BooksDto(List<BookEntity> books) {
        count = books.size();
        this.books = new ArrayList<>();
        books.forEach(book -> {
            this.books.add(BookDto.toBookDto(book));
        });
    }

    public BooksDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
