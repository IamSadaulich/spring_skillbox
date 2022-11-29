package org.example.web.controllers;


import org.apache.log4j.Logger;
import org.example.app.exceptions.FileNotSelectedException;
import org.example.app.services.BookService;
import org.example.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;
    private String[] modelAttributes = {"book", "bookIdToRemove", "bookSizeToRemove", "bookList", "bookAuthorToRemove", "bookTitleToRemove"};

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookTitleToRemove", new BookTitleToRemove());
        model.addAttribute("bookAuthorToRemove", new BookAuthorToRemove());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookSizeToRemove", new BookSizeToRemove());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookTitleToRemove", new BookTitleToRemove());
            model.addAttribute("bookAuthorToRemove", new BookAuthorToRemove());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookSizeToRemove", new BookSizeToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookTitleToRemove", new BookTitleToRemove());
            model.addAttribute("bookAuthorToRemove", new BookAuthorToRemove());
            model.addAttribute("bookSizeToRemove", new BookSizeToRemove());
            return "book_shelf";
        } else {
            bookService.removeBookById(bookIdToRemove.getId());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllBookBySize")
    public String removeAllBookBySize(@Valid BookSizeToRemove bookSizeToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookTitleToRemove", new BookTitleToRemove());
            model.addAttribute("bookAuthorToRemove", new BookAuthorToRemove());
            return "book_shelf";
        } else {
            bookService.removeAllBookBySize(bookSizeToRemove.getSize());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeAllBookByTitle")
    public String removeAllBookByTitle(@Valid BookTitleToRemove bookTitle, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookSizeToRemove", new BookSizeToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookAuthorToRemove", new BookAuthorToRemove());
            return "book_shelf";
        } else {

        }
        bookService.removeAllBookByTitle(bookTitle.getTitle());
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeAllBookByAuthor")
    public String removeAllBookByAuthor(@Valid BookAuthorToRemove bookAuthor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookSizeToRemove", new BookSizeToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookTitleToRemove", new BookTitleToRemove());
            return "book_shelf";
        } else {
            bookService.removeAllBookByAuthor(bookAuthor.getAuthor());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        logger.info("upload file: ");
        if (file.isEmpty()) {
            throw new FileNotSelectedException("File not selected for download.");
        }
        String name = file.getOriginalFilename();
        byte[] bytes = file.getBytes();

        // create dir
        String rootPath = System.getProperty("catalina.home");  // свойство отражающее путь до папки содержащей файлы нащего сервера
        File dir = new File(rootPath + File.separator + "external_uploads");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // create File
        File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        logger.info("new file saved at: " + serverFile.getAbsolutePath());

        return "redirect:/books/shelf";
    }

    @ExceptionHandler(FileNotSelectedException.class)
    public String handleError(Model model, Exception e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/error_page";
    }
}
