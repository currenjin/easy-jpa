package com.currenjin.easy_jpa;

import com.currenjin.easy_jpa.domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class EasyJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner example(EntityManagerFactory entityManagerFactory) {
		return (args) -> {
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			entityManager.getTransaction().begin();

			// 엔티티 생성 및 저장 예제
			Book book = new Book("Test Driven Development", "Currenjin");
			entityManager.persist(book);

			entityManager.getTransaction().commit();

			// 엔티티 조회 예제
			Book foundBook = entityManager.find(Book.class, book.getId());
			System.out.println("Found Book: " + foundBook.getTitle() + " by " + foundBook.getAuthor());

			entityManager.close();
		};
	}
}
