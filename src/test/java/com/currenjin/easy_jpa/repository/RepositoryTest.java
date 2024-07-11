package com.currenjin.easy_jpa.repository;


import com.currenjin.easy_jpa.domain.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Repository.class))
public class RepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private Repository<Book, Long> bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new RepositoryImpl<>(Book.class);
        ((RepositoryImpl<Book, Long>) bookRepository).setEntityManager(entityManager);
    }

    @Test
    void testSaveAndFindById() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);

        Book foundBook = bookRepository.findById(book.getId()).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void testFindAll() {
        Book book1 = new Book("Test Book 1", "Test Author 1");
        Book book2 = new Book("Test Book 2", "Test Author 2");
        bookRepository.save(book1);
        bookRepository.save(book2);

        Iterable<Book> books = bookRepository.findAll();

        assertThat(books).hasSize(2);
    }

    @Test
    void testDeleteById() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);

        bookRepository.deleteById(book.getId());
        Book foundBook = bookRepository.findById(book.getId()).orElse(null);

        assertThat(foundBook).isNull();
    }
}

