package com.mitrais.rms.springboot.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.ShelfService;

@RestController
@RequestMapping("/api")
public class ShelfController {

	@Autowired
	private ShelfService shelfService;
	
	@GetMapping("/shelfs/{shelfId}")
	public ResponseEntity<Shelf> getShelfById(@PathVariable int shelfId) throws Exception
	{
		Shelf shelf = shelfService.displayShelfById(shelfId);
		if (shelf!=null) 
		{
			return new ResponseEntity<>(shelf, HttpStatus.OK);
		}else 
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	/*create new books*/
	@PostMapping("/shelfs")
	public ResponseEntity<?> createShelf(@RequestBody Shelf shelf) {
		try {
			shelfService.createShelf(shelf);
			return new ResponseEntity<>(shelf,HttpStatus.CREATED);
		}catch(DataAccessException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
			//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
