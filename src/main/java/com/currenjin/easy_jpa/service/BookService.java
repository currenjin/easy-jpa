package com.currenjin.easy_jpa.service;

import com.currenjin.easy_jpa.domain.Book;
import com.currenjin.easy_jpa.repository.EasyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private final EasyRepository<Book, Long> easyRepository;

    public BookService(EasyRepository<Book, Long> easyRepository) {
        this.easyRepository = easyRepository;
    }

    @Transactional
    public Book saveBook(Book book) {
        return easyRepository.save(book);
    }

    @Transactional
    public Book findBookById(Long id) {
        return easyRepository.findById(id).orElse(null);
    }
}
