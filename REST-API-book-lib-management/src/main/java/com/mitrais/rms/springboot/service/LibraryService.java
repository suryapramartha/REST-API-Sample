package com.mitrais.rms.springboot.service;

import java.util.Map;

public interface LibraryService {
	
	Map<String, Object> addBookToShelf(int shelfId,int bookId);//update book status, add current capacity
	Map<String, Object> removeBookFromShelf(int shelfId,int bookId);//update book status, current capacity
	
}
