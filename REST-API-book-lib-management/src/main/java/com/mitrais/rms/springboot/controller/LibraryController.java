package com.mitrais.rms.springboot.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.rms.springboot.model.Library;
import com.mitrais.rms.springboot.service.LibraryService;

@RestController
@RequestMapping("/api")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@PostMapping("/libraries/") 
	public ResponseEntity<?> addBookToShelf(@RequestBody Library library)
	{
		Map<String, Object> result = libraryService.addBookToShelf(library.getShelfId(), library.getBookId());
		String message = (String) result.get("message");
		HttpStatus status = (HttpStatus) result.get("status");
		return new ResponseEntity<>(Collections.singletonMap("message", message),status);
	}
	
	@DeleteMapping("/libraries/{shelfId}/books/{bookId}") 
	public ResponseEntity<?> removeBookFromShelves(@PathVariable int shelfId, @PathVariable int bookId)
	{
		Map<String, Object> result = libraryService.removeBookFromShelf(shelfId, bookId);
		String message = (String) result.get("message");
		HttpStatus status = (HttpStatus) result.get("status");
		return new ResponseEntity<>(Collections.singletonMap("message", message),status);
	}
	
}
