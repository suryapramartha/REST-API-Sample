package com.mitrais.rms.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mitrais.rms.springboot.dao.ShelfRepository;
import com.mitrais.rms.springboot.model.Shelf;

@RunWith(SpringRunner.class)
@DataJpaTest 
public class ShelfRepositoryTest 
{
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Before
	public void setUp() {
		Shelf shelf1 = new Shelf(1,5,3);
		shelfRepository.save(shelf1);
	}
	
	@After
	public void tearDown() {
		shelfRepository.deleteAll();
	}

	//@Test
	public void updateCurrentCapacity_testExpectOKandReturn1() 
	{
		int shelfId = 0;
		int currentcapacity = 3;
		int i = shelfRepository.updateCurrentCapacity(shelfId, currentcapacity);
		assertEquals(1,i);
	}
	
	@Test
	public void updateCurrentCapacity_testExpectOKandReturn0() {
		
		int shelfId = 2;
		int currentcapacity = 1;
		int i = shelfRepository.updateCurrentCapacity(shelfId, currentcapacity);
		assertEquals(0,i);
	}
}
