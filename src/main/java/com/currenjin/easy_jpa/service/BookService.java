package com.currenjin.easy_jpa.service;

import com.currenjin.easy_jpa.domain.Book;
import com.currenjin.easy_jpa.repository.Repository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final Repository<Book, Long> repository;

    public BookService(Repository<Book, Long> repository) {
        this.repository = repository;
    }

    @Transactional
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Transactional
    public Book findBookById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
