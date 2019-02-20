package com.mitrais.rms.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.rms.springboot.dao.ShelfRepository;
import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.ShelfService;

@Service
public class ShelfServiceImpl implements ShelfService {

	@Autowired
	private ShelfRepository shelfRepository;

	@Override
	public Shelf displayShelfById(int id) {
		return shelfRepository.findByShelfId(id);
	}

	@Override
	public void createShelf(Shelf shelf) {
		shelfRepository.save(shelf);
		
	}

	protected int updateCurrentCapacity(int shelfId, int currentcapacity) {
		return shelfRepository.updateCurrentCapacity(shelfId, currentcapacity);
	}

}
