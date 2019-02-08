package com.mitrais.rms.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mitrais.rms.springboot.model.Book;
import com.mitrais.rms.springboot.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookService.getAllBook();
	}
	
	@GetMapping("/books/{bookId}")
	public Optional<Book> getBookById(@PathVariable int bookId) throws Exception {
		Optional<Book> book = bookService.getSelectedBookById(bookId);
		if (book == null ) {
			throw new Exception("Book ID not found");
		}
		return book;
	}
	
	@GetMapping("/bookStatus/{statusBook}")
	public List<Book> getBookByStatus(@PathVariable String statusBook) throws Exception {
		List<Book> book = bookService.getSelectedBookByStatus(statusBook);
		if (book == null ) {
			throw new Exception("Book Status not found");
		}
		return book;
	}
	
	@GetMapping("/bookStatus/{statusBook}/{title}")
	public List<Book> getBookByStatusAndTitle(@PathVariable String statusBook,@PathVariable String title) throws Exception {
		List<Book> book = bookService.findByBookStatusAndBookTitle(statusBook, title);
		if (book == null ) {
			throw new Exception("Book Status not found");
		}
		return book;
	}
	
	@PostMapping("/books")
	public Book createbook(@RequestBody Book book) {
		book.setBookId(0);
		book.setBookStatus("not_shelved");
		bookService.createBook(book);
		return book;
	}
}
