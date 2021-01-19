package com.example.spring.mongo.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;

public class TestHelper {
	
	public static List<Book> getBookList() {
		List<Book> books = new ArrayList<>();
		Book book = new Book(101, "Mockito", "pubisher1", 15.00);
		Book book1 = new Book(102, "Junit", "pubisher", 10.00);
		books.add(book);
		books.add(book1);
		return books;
	}
	
	public static List<BookDTO> getBookDTOList() {
		List<BookDTO> books = new ArrayList<>();
		BookDTO bookDTO = new BookDTO(101, "Mockito", "pubisher1", 15.00);
		BookDTO bookDTO1 = new BookDTO(102, "Junit", "pubisher", 10.00);
		books.add(bookDTO);
		books.add(bookDTO1);
		return books;
	}
	
	

}
