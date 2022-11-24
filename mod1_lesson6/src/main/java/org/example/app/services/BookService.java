package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void removeAllBookByAuthor(String author) {
        for (Book book : getAllBooks()) {
            if (book.getAuthor().equals(author)) {
                removeBookById(book.getId());
            }
        }
    }

    public void removeAllBookByTitle(String title) {
        for (Book book : getAllBooks()) {
            if (book.getTitle().equals(title)) {
                removeBookById(book.getId());
            }
        }
    }

    public void removeAllBookBySize(Integer size) {
        for (Book book : getAllBooks()) {
            if (book.getSize().equals(size)) {
                removeBookById(book.getId());
            }
        }
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }
}
