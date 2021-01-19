/**
 * 
 */
package com.example.spring.mongo.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;

/**
 *
 */

public class BookMapper {
	private BookMapper() {}
	
	public static Book bookDtoToBookMapper(BookDTO bookDTO) {
			return new Book(bookDTO.getId(), 
					bookDTO.getName(),
					bookDTO.getAuthor(),
					bookDTO.getPrice()
					);
		}
	
	
	public static BookDTO bookToBookDtoMapper(Book book) {
		return new BookDTO(book.getId(), 
				book.getName(),
				book.getAuthor(),
				book.getPrice()
				);
	}


	public static List<BookDTO> bookToBookDtoMapperList(List<Book> books) {
		List<BookDTO> bookDTOs = new ArrayList<>();
		books.stream().forEach(book -> bookDTOs
				.add(new BookDTO(book.getId(),
						book.getName(), 
						book.getAuthor(), 
						book.getPrice()
						)));
		return bookDTOs;
	}
}
