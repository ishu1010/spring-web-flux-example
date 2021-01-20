package com.example.spring.mongo.service;

import static com.example.spring.mongo.test.utils.TestHelper.getBookDTOList;
import static com.example.spring.mongo.test.utils.TestHelper.getBookList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public class BookServiceTest {
	@Mock
	BookRepository repository;
	
	@Spy
	@InjectMocks
	BookService service;
	private Book book;
	private BookDTO bookDTO;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		book = new Book(101, "Mokito", "publisher", 15.00);
		bookDTO = new BookDTO(101, "Mokito", "publisher", 15.00);
	}
	
	@Test
	public void getAllResource_shouldReturnSuccessfully() {
		when(repository.findAll()).thenReturn(Flux.fromIterable( getBookList()));
		Flux<Book> books= service.getAllResource();
		verify(service, times(1)).getAllResource();
	}
	
	@Test
	public void saveBook_shouldReturnSuccessful() {
		when(repository.save(Mockito.<Book>any())).thenReturn(Mono.just(book));
		Mono<Book> book= service.createUpdateResource(bookDTO);
		verify(service, times(1)).createUpdateResource(Mockito.<BookDTO>any());
	}
	
	@Test
	public void updateBook_shouldReturnSuccessful() {
		when(repository.save(Mockito.<Book>any())).thenReturn(Mono.just(book));
		Mono<Book> book= service.updateResource(bookDTO);
		verify(service, times(1)).updateResource(Mockito.<BookDTO>any());
	}
	
	
	@Test
	public void deleteBook_shouldReturnSuccessful() {
		when(repository.delete(Mockito.<Book>any())).thenReturn(Mono.empty());
		service.deleteResource(bookDTO);
		verify(service, times(1)).deleteResource(Mockito.<BookDTO>any());
	}
}
