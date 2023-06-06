package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.AuthorEntity;
import com.example.MyBookShopApp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<AuthorEntity>> getAuthorsMap() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities.stream().collect(Collectors.groupingBy((AuthorEntity a) -> {return a.getName().substring(0,1);}));
    }
}
