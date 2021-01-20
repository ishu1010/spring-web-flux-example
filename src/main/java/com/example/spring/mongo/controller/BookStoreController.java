package com.example.spring.mongo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

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


	@GetMapping(path = "/catalogue")
	public Mono<ServerResponse> getBookCatalog() {
		Flux<Book> book = bookService.getAllResource();
		return ServerResponse.status(HttpStatus.OK).body(book, Book.class);
	}

	@PostMapping(path = "/save")
	public Mono<ServerResponse> saveBook(@RequestBody BookDTO bookDTO) {
		Mono<Book> book= bookService.createUpdateResource(bookDTO);
		return ServerResponse.status(HttpStatus.CREATED).body(book, Book.class);
	}

	@PutMapping(path = "/update")
	public Mono<ServerResponse> updateBookDetails(@RequestBody BookDTO bookDTO) {
		return saveBook(bookDTO);
	}

	@DeleteMapping(path = "/delete")
	public Mono<ServerResponse> deleteBook(@RequestBody BookDTO bookDTO) {
		bookService.deleteResource(bookDTO);
		return ServerResponse.status(HttpStatus.OK).build();
	}

}
