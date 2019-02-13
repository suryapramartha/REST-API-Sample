package com.mitrais.rms.springboot.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mitrais.rms.springboot.dao.BookRepository;
import com.mitrais.rms.springboot.model.Book;
import com.mitrais.rms.springboot.service.impl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	
	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void getAllBook_testTotalValue()
	{
		Book book1 = new Book(1,"whatever","LOL","Gde","not_shelved");
		Book book2 = new Book(2,"whatever2","LOL2","Gde2","not_shelved");

		List<Book> value = new ArrayList<Book>();
		value.add(book1);
		value.add(book2);
		when(bookRepository.findAll()).thenReturn(value);
		assertEquals(2, bookServiceImpl.getAllBook().size());
	}
	
	@Test
	public void findByBookStatusAndBookTitle_testObject()
	{	
		Book book1 = new Book(1,"whatever","LOL","Gde","not_shelved");
		List<Book> value = new ArrayList<>();
		value.add(book1);
		when(bookRepository.findByBookStatusAndBookTitle(book1.getBookStatus(),book1.getBookTitle())).thenReturn(value);
					
		List<Book> expecteds = new ArrayList<>();
		expecteds.add(book1);
		assertEquals(expecteds, bookServiceImpl.findByBookStatusAndBookTitle(book1.getBookStatus(), book1.getBookTitle()));
	}
	
	@Test
	public void findByBookStatusAndBookTitle_testSize()
	{	
		List<Book> value = new ArrayList<>();
		String status = "not_shelved";
		String title = "LOL";
		Book book1 = new Book(1,"whatever","LOL","Gde","not_shelved");
		value.add(book1);
		
		when(bookRepository.findByBookStatusAndBookTitle(status,title)).thenReturn(value);
		assertEquals(1, bookServiceImpl.findByBookStatusAndBookTitle(status, title).size());
	}
}
