package com.mitrais.rms.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitrais.rms.springboot.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByBookStatus(String statusBook);
	
	@Query(value="SELECT u FROM Book u WHERE u.bookStatus = :bookStatus and u.bookTitle = :bookTitle")
	List<Book> findByBookStatusAndBookTitle(@Param("bookStatus") String statusBook, @Param("bookTitle") String statusTitle);
	
}
