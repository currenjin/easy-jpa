package com.currenjin.easy_jpa.repository;


import com.currenjin.easy_jpa.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EasyRepositoryImpl.class))
public class EasyRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private EasyRepository<Book, Long> bookEasyRepository;

    @BeforeEach
    void setUp() {
        bookEasyRepository = new EasyRepositoryImpl<>(Book.class);
        ((EasyRepositoryImpl<Book, Long>) bookEasyRepository).setEntityManager(entityManager);
    }

    @Test
    void testSaveAndFindById() {
        Book book = new Book("Test Book", "Test Author");
        bookEasyRepository.save(book);

        Book foundBook = bookEasyRepository.findById(book.getId()).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void testFindAll() {
        Book book1 = new Book("Test Book 1", "Test Author 1");
        Book book2 = new Book("Test Book 2", "Test Author 2");
        bookEasyRepository.save(book1);
        bookEasyRepository.save(book2);

        Iterable<Book> books = bookEasyRepository.findAll();

        assertThat(books).hasSize(2);
    }

    @Test
    void testDeleteById() {
        Book book = new Book("Test Book", "Test Author");
        bookEasyRepository.save(book);

        bookEasyRepository.deleteById(book.getId());
        Book foundBook = bookEasyRepository.findById(book.getId()).orElse(null);

        assertThat(foundBook).isNull();
    }
}

