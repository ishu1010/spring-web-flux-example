package com.example.spring.mongo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.repository.BookRepository;
import com.example.spring.mongo.utils.BookMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public Flux<Book> getAllResource() {
		return bookRepository.findAll().switchIfEmpty(Flux.empty());
	}

	@Transactional
	public Mono<Book> createUpdateResource(BookDTO bookDTO) {
		return bookRepository.save(BookMapper.bookDtoToBookMapper(bookDTO));

	}

	@Transactional
	public Mono<Book> updateResource(BookDTO bookDTO) {
		return createUpdateResource(bookDTO);
	}

	@Transactional
	public void deleteResource(BookDTO bookDTO) {
		bookRepository.delete(BookMapper.bookDtoToBookMapper(bookDTO));
	}

}
