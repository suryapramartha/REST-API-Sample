package com.mitrais.rms.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.mitrais.rms.springboot.model.Book;

public interface BookService 
{
	void deleteById(int id);
	void createBook(Book o);
	Optional<Book> getSelectedBookById(int id);
	List<Book> getSelectedBookByStatus(String status); 
	List<Book> findByBookStatusAndBookTitle(String statusBook, String statusTitle);
	int addBookToShelf(int shelfId,int bookId);//update book status, add current capacity
	int removeBookFromShelf(int bookId);//update book status, current capacity
	
}
