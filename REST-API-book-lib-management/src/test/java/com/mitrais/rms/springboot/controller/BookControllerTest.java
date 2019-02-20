package com.mitrais.rms.springboot.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitrais.rms.springboot.model.Book;
import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.BookService;
import com.mitrais.rms.springboot.service.ShelfService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@MockBean 
	private ShelfService shelfService;
	
	@Test
	public void getBookByStatusAndTitle_testExpectOKandReturnValue() throws Exception 
	{
		List<Book> listBook = new ArrayList<>();
		Book book1 = new Book(1,"whatever","TheMightyDragonkin","Gde","not_shelved");
		listBook.add(book1);
		when(bookService.findByBookStatusAndBookTitle(listBook.get(0).getBookStatus(),listBook.get(0).getBookTitle())).thenReturn(listBook);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/booksBy/")
				.param("bookStatus", "not_shelved")
				.param("bookTitle", "TheMightyDragonkin")
				.accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("[{}]"))
				.andReturn();
//		JSONAssert.assertEquals("[{bookId:1,bookIsbn:whatever,bookTitle:TheMightyDragonkin,bookAuthor:Gde,bookStatus:not_shelved,shelfId:0}]",
//				res.getResponse().getContentAsString(),true);
	}
	
	@Test
	public void getBookByStatusAndTitle_testExpectNO_CONTENTandReturnEmpty() throws Exception 
	{
		List<Book> listBook = new ArrayList<>();
		Book book1 = new Book(1,"whatever","TheMightyDragonkin","Gde","not_shelved");
		listBook.add(book1);
		when(bookService.findByBookStatusAndBookTitle(listBook.get(0).getBookStatus(),listBook.get(0).getBookTitle())).thenReturn(listBook);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/booksBy/")
				.param("bookStatus", "shelved")
				.param("bookTitle", "TheMightyDragonkin")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNoContent())
				.andReturn();
//		JSONAssert.assertEquals("[{bookId:1,bookIsbn:whatever,bookTitle:TheMightyDragonkin,bookAuthor:Gde,bookStatus:not_shelved}]",
//				res.getResponse().getContentAsString(),true);
		assertEquals("",res.getResponse().getContentAsString());
	}
	
	@Test
	public void getBookByStatus_testExpectOKandReturnValue() throws Exception 
	{
		List<Book> listBook = new ArrayList<>();
		Book book1 = new Book(1,"whatever","TheMightyDragonkin","Gde","not_shelved");
		listBook.add(book1);
		when(bookService.getSelectedBookByStatus(listBook.get(0).getBookStatus())).thenReturn(listBook);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/")
				.param("bookStatus", "not_shelved")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
		JSONAssert.assertEquals("[{bookId:1,bookIsbn:whatever,bookTitle:TheMightyDragonkin,bookAuthor:Gde,bookStatus:not_shelved,shelfId:0}]",
				res.getResponse().getContentAsString(),true);
	}
	
	@Test
	public void getBookByStatus_testExpectNO_CONTENTandReturnEmpty() throws Exception 
	{
		List<Book> listBook = new ArrayList<>();
		Book book1 = new Book(1,"whatever","TheMightyDragonkin","Gde","not_shelved");
		listBook.add(book1);
		when(bookService.getSelectedBookByStatus(listBook.get(0).getBookStatus())).thenReturn(listBook);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/")
				.param("bookStatus", "shelved")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNoContent())
				.andReturn();
		assertEquals("",res.getResponse().getContentAsString());
	}
	
	@Test
	public void createbook_testExpectCREATEDandReturnValue() throws Exception 
	{		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/books/")
				.content("{\"bookIsbn\" : \"whatever\",\"bookTitle\" : \"TheMightyDragonkin\",\"bookAuthor\" : \"Gde\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andReturn();
		JSONAssert.assertEquals("{bookId:0,bookIsbn:whatever,bookTitle:TheMightyDragonkin,bookAuthor:Gde,bookStatus:not_shelved,shelfId:0}",
				res.getResponse().getContentAsString(),true);
		
	}
	
//	@Test
	public void addBookToShelf_testExpectOKandReturnOK() throws Exception 
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 0);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.addBookToShelf(shelf.getShelfId(), bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookToShelves/")
				.param("shelfId", String.valueOf(shelf.getShelfId()))
				.param("bookId", String.valueOf(bookId))
				.accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{message : OK}"))
				.andReturn();
//		assertEquals("OK",res.getResponse().getContentAsString());
	}
	
	//@Test
	public void addBookToShelf_testExpectNOT_FOUNDandReturnBOOK_NOT_FOUND() throws Exception 
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 0);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.addBookToShelf(shelf.getShelfId(), bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookToShelves/")
				.param("shelfId", String.valueOf(shelf.getShelfId()))
				.param("bookId", String.valueOf(2))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound())
				.andReturn();
		assertEquals("Book not found or already added",res.getResponse().getContentAsString());
	}
	
//	@Test
	public void addBookToShelf_testExpectNOT_FOUNDandReturnSHELF_NOT_FOUND() throws Exception 
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 0);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.addBookToShelf(shelf.getShelfId(), bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookToShelves/")
				.param("shelfId", String.valueOf(2))
				.param("bookId", String.valueOf(bookId))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound())
				.andReturn();
		assertEquals("Shelf not found",res.getResponse().getContentAsString());
	}
	
	//@Test
	public void addBookToShelf_testExpectUNPROCESSandReturnFULL() throws Exception 
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 5);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.addBookToShelf(shelf.getShelfId(), bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookToShelves/")
				.param("shelfId", String.valueOf(shelf.getShelfId()))
				.param("bookId", String.valueOf(bookId))
				.accept(MediaType.APPLICATION_JSON);
//		MvcResult res = 
				mockMvc.perform(requestBuilder)
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{message : Can't add book, Shelf capacity is full}"))
				.andReturn();
		//assertEquals("Can't add book, Shelf capacity is full",res.getResponse().getContentAsString());
	}
	
	//@Test
	public void removeBookFromShelves_testExpectOKandreturnOK() throws Exception
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 1);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.removeBookFromShelf(bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/dropBookFromShelves/")
				.param("shelfId", String.valueOf(shelf.getShelfId()))
				.param("bookId", String.valueOf(bookId))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
		assertEquals("OK",res.getResponse().getContentAsString());
	}
	
	//@Test
	public void removeBookFromShelves_testExpectNOT_FOUNDandreturnBOOK_NOT_FOUND() throws Exception
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 1);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.removeBookFromShelf(bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/dropBookFromShelves/")
				.param("shelfId", String.valueOf(shelf.getShelfId()))
				.param("bookId", String.valueOf(3))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound())
				.andReturn();
		assertEquals("Book not found or already dropped",res.getResponse().getContentAsString());
	}
	
	//@Test
	public void removeBookFromShelves_testExpectNOT_FOUNDandreturnSHELF_NOT_FOUND() throws Exception
	{		
		int bookId=1;
		Shelf shelf = new Shelf(1, 5, 1);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf);
		when(bookService.removeBookFromShelf(bookId)).thenReturn(1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/dropBookFromShelves/")
				.param("shelfId", String.valueOf(8))
				.param("bookId", String.valueOf(bookId))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound())
				.andReturn();
		assertEquals("Shelf not found",res.getResponse().getContentAsString());
	}

}
