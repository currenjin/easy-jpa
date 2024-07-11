package com.currenjin.easy_jpa;

import com.currenjin.easy_jpa.domain.Book;
import com.currenjin.easy_jpa.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EasyJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookService bookService) {
		return (args) -> {
			Book book = new Book("JPA", "currenjin");
			bookService.saveBook(book);

			Book foundBook = bookService.findBookById(book.getId());
			System.out.println("Found Book: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
		};
	}
}
