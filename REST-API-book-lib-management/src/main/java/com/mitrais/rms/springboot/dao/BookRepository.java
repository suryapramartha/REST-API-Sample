package com.mitrais.rms.springboot.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitrais.rms.springboot.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByBookStatus(String statusBook);
	
	@Query(value="SELECT u FROM Book u WHERE u.bookStatus = :bookStatus and u.bookTitle = :bookTitle")
	List<Book> findByBookStatusAndBookTitle(@Param("bookStatus") String statusBook, @Param("bookTitle") String statusTitle);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Book u set u.shelfId= :shelfId, u.bookStatus='shelved' WHERE u.bookId = :bookId and u.bookStatus='not_shelved'")
	int addBookToShelf(@Param("shelfId") int shelfId, @Param("bookId") int bookId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Book u set u.shelfId= 0, u.bookStatus='not_shelved' WHERE u.bookId = :bookId and u.bookStatus='shelved'")
	int removeBookFromShelf(@Param("bookId") int bookId);
	
}
