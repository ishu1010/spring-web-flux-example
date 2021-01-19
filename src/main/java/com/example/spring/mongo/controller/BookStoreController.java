package com.example.spring.mongo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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


	@GetMapping(path = "/catalogue")
	public Flux<Book> getBookCatalog() {
		return bookService.getAllResource();
	}

	@PostMapping(path = "/save")
	public Mono<Book> saveBook(@RequestBody BookDTO bookDTO) {
		return bookService.createUpdateResource(bookDTO);
	}

	@PutMapping(path = "/update")
	public Mono<Book> updateBookDetails(@RequestBody BookDTO bookDTO) {
		return bookService.createUpdateResource(bookDTO);
	}

	@DeleteMapping(path = "/delete")
	public Mono <String> deleteBook(@RequestBody BookDTO bookDTO) {
		bookService.deleteResource(bookDTO);
		return Mono.empty();
	}

}
