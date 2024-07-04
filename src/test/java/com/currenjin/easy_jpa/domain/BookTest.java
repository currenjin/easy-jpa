package com.currenjin.easy_jpa.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String UPDATED_TITLE = "updatedTitle";
    private static final String UPDATED_AUTHOR = "updatedAuthor";

    @Test
    void create_book() {
        assertDoesNotThrow(() -> new Book("title", "author"));
    }

    @Test
    void get_field() {
        Book book = new Book(TITLE, AUTHOR);

        assertEquals(TITLE, book.getTitle());
        assertEquals(AUTHOR, book.getAuthor());
    }

    @Test
    void set_field() {
        Book book = new Book(TITLE, AUTHOR);

        book.setTitle(UPDATED_TITLE);
        book.setAuthor(UPDATED_AUTHOR);

        assertEquals(UPDATED_TITLE, book.getTitle());
        assertEquals(UPDATED_AUTHOR, book.getAuthor());
    }
}