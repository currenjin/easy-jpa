package com.currenjin.easy_jpa.service;

import com.currenjin.easy_jpa.domain.Book;
import com.currenjin.easy_jpa.repository.Repository;
import com.currenjin.easy_jpa.repository.RepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Repository.class, BookService.class}))
public class BookServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    private BookService bookService;
    private Repository<Book, Long> bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new RepositoryImpl<>(Book.class);
        ((RepositoryImpl<Book, Long>) bookRepository).setEntityManager(entityManager);
        bookService = new BookService(bookRepository);
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
        bookRepository.save(book);

        Book foundBook = bookService.findBookById(book.getId());

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }
}