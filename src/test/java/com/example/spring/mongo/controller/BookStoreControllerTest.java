package com.example.spring.mongo.controller;

import static com.example.spring.mongo.test.utils.TestHelper.getBookDTOList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.spring.mongo.dto.BookDTO;
import com.example.spring.mongo.model.Book;
import com.example.spring.mongo.service.BookService;
import com.example.spring.mongo.test.utils.TestHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookStoreControllerTest {

	private MockMvc mvc;

	@Mock
	private BookService bookService;

	@InjectMocks
	@Spy
	private BookStoreController bookStoreController;
	private BookDTO bookDTO;
	private ObjectMapper mapper;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
		mapper = new ObjectMapper();
		bookDTO = new BookDTO(101, "Mokito", "publisher", 15.00);
	}

	@Test
	public void getBookCatalog_shouldReturnSuccessful() throws Exception {
		when(bookService.getAllResource()).thenReturn((Flux<Book>) getBookDTOList());
		MvcResult result = mvc.perform(get("/book/catalogue").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		List<BookDTO> actual = mapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<BookDTO>>() {});
		Assertions.assertEquals(getBookDTOList().toString(), actual.toString());
		verify(bookService, times(1)).getAllResource();
	}
	
	@Test
	public void saveBook_shouldReturnSuccessful() throws Exception {
		
		String jsonRequestBody = mapper.writeValueAsString(bookDTO);
		//when(bookService.createUpdateResource(Mockito.<BookDTO>any())).thenReturn(bookDTO);
		MvcResult result = mvc.perform(post("/book/save-resource").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonRequestBody))
				.andExpect(status().is2xxSuccessful()).andReturn();
		Assertions.assertEquals(jsonRequestBody, result.getResponse().getContentAsString());
		verify(bookService, times(1)).createUpdateResource(Mockito.<BookDTO>any());
	}
	
	@Test
	public void updateBook_shouldReturnSuccessful() throws Exception {
		String jsonRequestBody = mapper.writeValueAsString(bookDTO);
		//when(bookService.updateResource(Mockito.<BookDTO>any())).thenReturn(bookDTO);
		MvcResult result = mvc.perform(put("/book/update-resource").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonRequestBody))
				.andExpect(status().is2xxSuccessful()).andReturn();
		Assertions.assertEquals(jsonRequestBody, result.getResponse().getContentAsString());
		verify(bookService, times(1)).updateResource(Mockito.<BookDTO>any());
	}
	
	@Test
	public void deleteBook_shouldReturnSuccessful() throws Exception {
		String jsonRequestBody = mapper.writeValueAsString(bookDTO);
		doNothing().when(bookService).deleteResource(Mockito.<BookDTO>any());
		MvcResult result = mvc.perform(delete("/book/delete-resource").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonRequestBody))
				.andExpect(status().is2xxSuccessful()).andReturn();
		Assertions.assertEquals("book deleted!", result.getResponse().getContentAsString());
		verify(bookService, times(1)).deleteResource(Mockito.<BookDTO>any());
	}
}
