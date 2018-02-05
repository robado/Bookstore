package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("Saving books to repository");
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));

			log.info("Fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

			}
		};
	}



}
