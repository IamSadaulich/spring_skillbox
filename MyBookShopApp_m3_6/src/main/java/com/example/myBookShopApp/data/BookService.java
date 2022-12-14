package com.example.myBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Logger;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData(){

        Logger.getLogger(BookService.class.getName()).info("Hello");
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rownum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author_id"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            return book;
        });
        Logger.getLogger(BookService.class.getName()).info(books.toString());
        return new ArrayList<>(books);
    }

    public Set<Map.Entry<Character, List<Author>>> getSortedAuthors() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rownum)->{
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
        Collections.sort(authors, new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        HashMap<Character, List<Author>> sortedAuthors = new HashMap<>();
        authors.forEach(author -> {
            char firstLetter = author.getName().charAt(0);
            if (sortedAuthors.containsKey(firstLetter)) {
                sortedAuthors.get(firstLetter).add(author);
            } else {
                List<Author> authorList = new ArrayList<>();
                authorList.add(author);
                sortedAuthors.put(firstLetter, authorList);
            }
        });

        return sortedAuthors.entrySet();
    }
}
