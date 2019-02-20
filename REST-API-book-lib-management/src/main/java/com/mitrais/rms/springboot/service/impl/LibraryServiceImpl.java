package com.mitrais.rms.springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private ShelfServiceImpl shelfServiceImpl;
	
	@Override
	public Map<String,Object> addBookToShelf(int shelfId, int bookId) {
		int currentCapacity = 0;
		Map<String,Object> result = new HashMap<>();
		String message = null;
		Shelf shelf = shelfServiceImpl.displayShelfById(shelfId);
		if(shelf != null) 
		{
			currentCapacity = shelf.getCurrentCapacity();
			if (currentCapacity >= shelf.getMaxCapacity()) 
			{
				message = "Can't add book, Shelf capacity is full";
				result.put("message", message);
				result.put("status",HttpStatus.UNPROCESSABLE_ENTITY);
				return result;				
			}else 
			{
				int added = bookServiceImpl.addBookToShelf(shelfId, bookId);
				if(added==1) 
				{
					currentCapacity += 1;
					shelfServiceImpl.updateCurrentCapacity(shelfId, currentCapacity);
					message = "OK";
					result.put("message", message);
					result.put("status",HttpStatus.OK);
					return result;
				}else 
				{
					message = "Book not found or already added";
					result.put("message", message);
					result.put("status",HttpStatus.NOT_FOUND);
					return result;
				}
			}	
		} else
		{
			message = "Shelf not found";
			result.put("message", message);
			result.put("status",HttpStatus.NOT_FOUND);
			return result;
		}
	}

	@Override
	public Map<String,Object> removeBookFromShelf(int shelfId,int bookId) {
		int currentCapacity= 0;
		Map<String,Object> result = new HashMap<>();
		String message = null;
		Shelf shelf = shelfServiceImpl.displayShelfById(shelfId);
		if(shelf != null) 
		{
			currentCapacity = shelf.getCurrentCapacity();
			int added = bookServiceImpl.removeBookFromShelf(bookId);
			if(added==1) 
			{
				currentCapacity -= 1;
				shelfServiceImpl.updateCurrentCapacity(shelfId, currentCapacity);
				message = "OK";
				result.put("message", message);
				result.put("status",HttpStatus.OK);
				return result;	

			}else 
			{
				message = "Book not found or already dropped";
				result.put("message", message);
				result.put("status",HttpStatus.NOT_FOUND);
				return result;	
			}
				
		} else
		{
			message = "Shelf not found";
			result.put("message", message);
			result.put("status",HttpStatus.NOT_FOUND);
			return result;	
		}
	}

}
