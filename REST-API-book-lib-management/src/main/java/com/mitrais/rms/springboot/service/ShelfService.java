package com.mitrais.rms.springboot.service;

import com.mitrais.rms.springboot.model.Shelf;

public interface ShelfService {
	
	void createShelf(Shelf shelf);
    Shelf displayShelfById(int id);
}
