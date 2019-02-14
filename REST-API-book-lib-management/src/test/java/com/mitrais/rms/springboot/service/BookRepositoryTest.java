package com.mitrais.rms.springboot.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitrais.rms.springboot.dao.BookRepository;
import com.mitrais.rms.springboot.model.Book;

@RunWith(SpringRunner.class)
//@DataJpaTest //how to use this.
@SpringBootTest 
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	
	@Test
	public void findAll_test() {
		List<Book> books = bookRepository.findAll();
		assertEquals(8, books.size());
	}
	
	@Test
	public void findByBookStatusAndBookTitle_testTotal() {
		String status = "not_shelved";
		String title = "The Mighty Dragonkin";
//		Book book1 = new Book(1,"whatever","The Mighty Dragonkin","Gde","not_shelved");
//		Book book2 = new Book(1,"whatever","The Mighty Dragonkin","Gde","shelved");
		List<Book> books = bookRepository.findByBookStatusAndBookTitle(status,title);
		assertEquals(1,books.size());
	}
	
	@Test
	public void findByBookStatusAndBookTitle_testObject() {
		List<Book> listBook = new ArrayList<>();
		Book book1 = new Book(1,"whatever","The Mighty Dragonkin","Gde","not_shelved");
		listBook.add(book1);
		String status ="not_shelved";
		String title = "The Mighty Dragonkin";
		List<Book> books = bookRepository.findByBookStatusAndBookTitle(status,title);
		assertEquals(book1,books.get(0));
	}
}
