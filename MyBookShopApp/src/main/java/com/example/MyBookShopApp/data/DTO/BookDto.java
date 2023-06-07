package com.example.MyBookShopApp.data.DTO;

import com.example.MyBookShopApp.data.BookEntity;
import com.example.MyBookShopApp.data.enums.StatusEnum;

public class BookDto {
    private int id;
    private String slug;
    private String image;
    private String authors;

    private String title;
    private int discount;
    private boolean isBestseller;
    private int rating;
    private StatusEnum status;
    private int price;
    private int discountPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public static BookDto toBookDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookEntity.getId());
        bookDto.setSlug(bookEntity.getSlug());
        bookDto.setImage(bookEntity.getImage());
        bookDto.setAuthors(bookEntity.getAuthors());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setDiscount(bookEntity.getDiscount());
        bookDto.setBestseller(bookEntity.isBestseller());
//        bookDto.setRating(bookEntity.getRating());
//        bookDto.setStatus(bookEntity.getStatus());
        bookDto.setPrice(bookEntity.getPrice());
        if (bookEntity.getDiscount() > 0) {
            bookDto.setDiscountPrice(bookDto.price / 100 * (100 - bookEntity.getDiscount()));
        }
        return bookDto;
    }
}
