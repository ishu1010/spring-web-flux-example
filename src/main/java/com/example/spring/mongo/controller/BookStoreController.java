package com.example.spring.mongo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.service.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * book store request handler
 * @author ishu1010	
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookStoreController {

	private final BookService bookService;


	@GetMapping(path = "/catalogue", produces = "application/json")
	public ResponseEntity<Flux<Book>> getBookCatalog() {
		Flux<Book> book = bookService.getAllResource();
		return new ResponseEntity<Flux<Book>>(book, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{book_id}", produces = "application/json")
	public ResponseEntity<Mono<Book>> getBookById(@PathVariable("book_id") int id) {
		Mono<Book> book = bookService.getBookById(id);
		return new ResponseEntity<Mono<Book>>(book, HttpStatus.OK);
	}

	@PostMapping(path = "/save", produces = "application/json")
	public ResponseEntity<Mono<Book>> saveBook(@RequestBody BookDTO bookDTO) {
		Mono<Book> book= bookService.createUpdateResource(bookDTO);
		return new ResponseEntity<Mono<Book>>(book, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update", produces = "application/json")
	public ResponseEntity<Mono<Book>> updateBookDetails(@RequestBody BookDTO bookDTO) {
		return saveBook(bookDTO);
	}

	@DeleteMapping(path = "/delete/{book_id}")
	public ResponseEntity<String> deleteBook(@PathVariable("book_id") int id) {
		bookService.deleteResource(id);
		return new ResponseEntity<String> ("Book deleted successfully!", HttpStatus.OK);
	}

}
