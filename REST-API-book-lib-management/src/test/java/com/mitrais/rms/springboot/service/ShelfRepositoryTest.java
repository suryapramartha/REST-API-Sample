package com.mitrais.rms.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitrais.rms.springboot.dao.ShelfRepository;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class ShelfRepositoryTest 
{
	@Autowired
	private ShelfRepository shelfRepository;

	@Test
	public void updateCurrentCapacity_testExpectOKandReturn1() {
		
		int shelfId = 5;
		int currentcapacity = 1;
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
