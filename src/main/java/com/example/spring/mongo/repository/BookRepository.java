package com.example.spring.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.mongo.model.Book;
@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, Integer> {

}
