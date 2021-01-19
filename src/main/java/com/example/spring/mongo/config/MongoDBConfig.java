package com.example.spring.mongo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.repository.BookRepository;

@EnableMongoRepositories(basePackages = "com.example.spring.mongo.repository")
@Configuration
public class MongoDBConfig {
	
	@Bean
	CommandLineRunner commandLineRunner (BookRepository bookRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				bookRepository.save(new Book(101, "Mongo in practice", "ishwar", 10.00));
				bookRepository.save(new Book(102, "mongo in practice edition 2", "ishwar", 13.00));
			}
		};
		
	}

}
