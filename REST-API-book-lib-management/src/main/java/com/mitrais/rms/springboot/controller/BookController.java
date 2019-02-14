package com.mitrais.rms.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mitrais.rms.springboot.model.Book;
import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.BookService;
import com.mitrais.rms.springboot.service.ShelfService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShelfService shelfService;
	/*
	 * 
	 * @GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> book = bookService.getAllBook();
		if(book.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Optional<Book>> getBookById(@PathVariable int bookId) throws Exception
	{
		Optional<Book> book = bookService.getSelectedBookById(bookId);
		if (book.isPresent()) 
		{
			return new ResponseEntity<>(book, HttpStatus.OK);
		}else 
		{
			throw new Exception("Book ID not found");
		}
		
	}
	*
	*
	*/
	
	/*Display books by status (shelved/not_shelved) and title // better use query string*/
	@GetMapping("/booksBy/") 
	public ResponseEntity<List<Book>> getBookByStatusAndTitle(@RequestParam("bookStatus") String bookStatus,@RequestParam("bookTitle") String bookTitle) throws Exception {
		try{
			List<Book> book = bookService.findByBookStatusAndBookTitle(bookStatus, bookTitle);
			if (book.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(book,HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*Display books by status (shelved/not_shelved)*/
	@GetMapping("/books/")
	public ResponseEntity<List<Book>> getBookByStatus(@RequestParam("bookStatus") String bookStatus) throws Exception {
		try{
			List<Book> book = bookService.getSelectedBookByStatus(bookStatus);
			if (book.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(book,HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/*create new books*/
	@PostMapping("/books")
	public ResponseEntity<Book> createbook(@RequestBody Book book) {
		try {
			book.setBookId(0);
			book.setBookStatus("not_shelved");
			bookService.createBook(book);
			return new ResponseEntity<>(book,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/*Add Book to shelf (update current capacity in Shelf and book status in Book)*/
	@GetMapping("/bookToShelves/") 
	public String addBookToShelf(@RequestParam("shelfId") int shelfId, @RequestParam("bookId") int bookId)
	{
		String result = null;
		int currentCapacity= 0;
		Shelf shelf = shelfService.displayShelfById(shelfId);
		if(shelf != null) 
		{
			currentCapacity = shelf.getCurrentCapacity();
			if (currentCapacity >= shelf.getMaxCapacity()) 
			{
				result = "Can't add book, Shelf capacity is full";
			}else 
			{
				int added = bookService.addBookToShelf(shelfId, bookId);
				if(added==1) 
				{
					currentCapacity += 1;
					shelfService.updateCurrentCapacity(shelfId, currentCapacity);
					result = "OK";
				}else 
				{
					result = "Book not found or already added";
				}
			}	
		} else
		{
			result = "Shelf not found";
		}
		return result;
	}
	
	/*Add Book to shelf (update current capacity in Shelf and book status in Book)*/
	@GetMapping("/dropBookFromShelves/") 
	public String removeBookFromShelves(@RequestParam("shelfId") int shelfId, @RequestParam("bookId") int bookId)
	{
		String result = null;
		int currentCapacity= 0;
		Shelf shelf = shelfService.displayShelfById(shelfId);
		if(shelf != null) 
		{
			currentCapacity = shelf.getCurrentCapacity();
			int added = bookService.removeBookFromShelf(bookId);
			if(added==1) 
			{
				currentCapacity -= 1;
				shelfService.updateCurrentCapacity(shelfId, currentCapacity);
				result = "OK";
			}else 
			{
				result = "Book not found or already dropped";
			}
				
		} else
		{
			result = "Shelf not found";
		}
		return result;
	}
}
