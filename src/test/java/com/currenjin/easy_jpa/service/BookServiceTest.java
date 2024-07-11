package com.currenjin.easy_jpa.service;

import com.currenjin.easy_jpa.domain.Book;
import com.currenjin.easy_jpa.repository.EasyRepository;
import com.currenjin.easy_jpa.repository.EasyRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {EasyRepository.class, BookService.class}))
public class BookServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    private BookService bookService;
    private EasyRepository<Book, Long> bookEasyRepository;

    @BeforeEach
    void setUp() {
        bookEasyRepository = new EasyRepositoryImpl<>(Book.class);
        ((EasyRepositoryImpl<Book, Long>) bookEasyRepository).setEntityManager(entityManager);
        bookService = new BookService(bookEasyRepository);
    }

    @Test
    void testSaveBook() {
        Book book = new Book("Test Book", "Test Author");
        bookService.saveBook(book);

        Book foundBook = bookService.findBookById(book.getId());

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void testFindBookById() {
        Book book = new Book("Test Book", "Test Author");
        bookEasyRepository.save(book);

        Book foundBook = bookService.findBookById(book.getId());

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }
}