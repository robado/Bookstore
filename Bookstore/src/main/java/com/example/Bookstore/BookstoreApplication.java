package com.example.Bookstore;

import com.example.Bookstore.model.User;
import com.example.Bookstore.model.UserRepository;
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
	public CommandLineRunner bookDemo(BookRepository repository, UserRepository urepo) {
		return (args) -> {
			log.info("Saving books to repository");
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));
			repository.save(new Book("Kaikki se valo jota emme näe", "Anthony Doerr", 2004, 902, 123));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepo.save(user1);
			urepo.save(user2);

			log.info("Fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

			}
		};
	}



}
