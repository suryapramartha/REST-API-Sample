package com.mitrais.rms.springboot.dao;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitrais.rms.springboot.model.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Integer>{

	Shelf findByShelfId(int id);

	@Transactional
	@Modifying
	@Query(value="UPDATE Shelf u set u.currentCapacity= :currentCapacity WHERE u.shelfId = :shelfId")
	int updateCurrentCapacity(@Param("shelfId") int shelfId, @Param("currentCapacity") int currentcapacity);
	
}
