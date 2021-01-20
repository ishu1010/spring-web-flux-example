package com.example.spring.mongo.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.service.BookService;
import com.example.spring.mongo.test.utils.TestHelper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@WebFluxTest(BookStoreController.class)
public class BookStoreControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private BookService bookService;
	
	private BookDTO bookDTO;
	private Book book;
	@BeforeEach
	public void setup() {
		bookDTO = BookDTO.builder().id(101).author("test").price(10.00).name("testing").build();
		 book = Book.builder().id(102).author("test1").name("testing1").price(12.00).build();
	}

	@Test
	public void getBookCatalog_shouldReturnSuccessful() throws Exception {
		when(bookService.getAllResource()).thenReturn(Flux.fromIterable(TestHelper.getBookList()));
		webTestClient.get().uri("/book/catalogue").accept(MediaType.APPLICATION_JSON).exchange().expectBodyList(Book.class).isEqualTo(TestHelper.getBookList());
	}
	
	@Test
	public void saveBook_shouldReturnSuccessful() throws Exception {
		when(bookService.createUpdateResource(bookDTO)).thenReturn(Mono.just(book));
		webTestClient.post().uri("/book/save").accept(MediaType.APPLICATION_JSON).body(Mono.just(bookDTO), BookDTO.class).exchange().expectStatus().isCreated().expectBody();
	}
	
	
	@Test	
	public void updateBook_shouldReturnSuccessful() throws Exception {
		when(bookService.updateResource(bookDTO)).thenReturn(Mono.just(book));
		webTestClient.put().uri("/book/update").accept(MediaType.APPLICATION_JSON).body(Mono.just(bookDTO), BookDTO .class).exchange().expectBody(Book.class).isEqualTo(book);
	}
	
	@Test
	public void deleteBook_shouldReturnSuccessful() throws Exception {
		webTestClient.delete().uri("/book/delete").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
	}
}
