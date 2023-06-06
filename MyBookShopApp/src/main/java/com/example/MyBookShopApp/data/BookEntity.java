package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.struct.book.links.Book2AuthorEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "DATE NOT NULL")
    private Date pubDate;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private boolean isBestseller;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private short discount;

    @OneToMany(mappedBy = "book")
    Set<Book2AuthorEntity> book2AuthorEntities;

    @Transient
    private Collection<AuthorEntity> authors;

    public Set<Book2AuthorEntity> getBook2AuthorEntities() {
        return book2AuthorEntities;
    }

    public Collection<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors() {
        Map<Integer, AuthorEntity> authorsMap = new TreeMap<>();
        book2AuthorEntities.forEach(b2a -> {
            authorsMap.put(b2a.getSortIndex(), b2a.getAuthor());
        });
        authors = authorsMap.values();
    }

    public String getAuthorsNames() {
        String authorsNames = "";
        for (AuthorEntity a : authors) {
            if (authorsNames.isEmpty()) {
                authorsNames = authorsNames.concat(a.getName());
            } else {
                authorsNames = authorsNames.concat(", ").concat(a.getName());
            }
        }
        return authorsNames;
    }

    public void setBook2AuthorEntities(Set<Book2AuthorEntity> book2AuthorEntities) {
        this.book2AuthorEntities = book2AuthorEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }
}
