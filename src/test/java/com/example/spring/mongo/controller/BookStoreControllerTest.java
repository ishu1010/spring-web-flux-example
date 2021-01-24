package com.example.spring.mongo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.service.BookService;
import com.example.spring.mongo.test.utils.TestHelper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
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
		bookDTO = new BookDTO(101, "Mokito", "publisher", 15.00);
		book = new Book(101, "Mokito", "publisher", 15.00);
	}

	@Test
	public void getBookCatalog_shouldReturnSuccessful() throws Exception {
		when(bookService.getAllResource()).thenReturn(Flux.fromIterable(TestHelper.getBookList()));
		webTestClient.get().uri("/book/catalogue").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

	}

	@Test
	public void getBookById_shouldReturnSuccessful() throws Exception {

		when(bookService.getBookById(Mockito.anyInt())).thenReturn(Mono.just(book));

		webTestClient.post().uri("/book/save").accept(MediaType.APPLICATION_JSON).body(Mono.just(book), Book.class)
				.exchange().expectStatus().isCreated();
	}

	@Test
	public void saveBook_shouldReturnSuccessful() throws Exception {

		when(bookService.createUpdateResource(bookDTO)).thenReturn(Mono.just(book));

		webTestClient.post().uri("/book/save").accept(MediaType.APPLICATION_JSON).body(Mono.just(book), Book.class)
				.exchange().expectStatus().isCreated();
	}

	@Test
	public void updateBook_shouldReturnSuccessful() throws Exception {
		when(bookService.updateResource(Mockito.<BookDTO>any())).thenReturn(Mono.just(book));
		webTestClient.put().uri("/book/update").accept(MediaType.APPLICATION_JSON).body(Mono.just(book), Book.class)
				.exchange().expectStatus().isCreated();
	}

	@Test
	public void deleteBook_shouldReturnSuccessful() throws Exception {
		doNothing().when(bookService).deleteResource(Mockito.anyInt());
		webTestClient.delete().uri("/book/delete/101").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk();
	}

}
