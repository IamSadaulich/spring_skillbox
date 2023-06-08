package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Page<BookEntity> findAllByPubDateAfterAndPubDateBeforeOrderByPubDateDesc(Date pubDateAfter, Date pubDateBefore, Pageable pageable);

    Page<BookEntity> findAllByPubDateAfterOrderByPubDateDesc(Date pubDate, Pageable pageable);

    Page<BookEntity> findAllByPubDateBeforeOrderByPubDateDesc(Date pubdate, Pageable pageable);
}
