package com.JBooks.J_books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class 	JBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBooksApplication.class, args);
	}

}
