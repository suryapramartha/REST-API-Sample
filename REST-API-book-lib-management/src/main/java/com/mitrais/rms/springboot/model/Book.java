package com.mitrais.rms.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="para_book")
public class Book 
{

	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	
	@Column(name="book_isbn")
	private String bookIsbn;
	
	@Column(name="book_title")
	private String bookTitle;
	
	@Column(name="book_author")
	private String bookAuthor;
	
	@Column(name="book_status")
	private String bookStatus;
	
	@Column(name="shelf_id")
	private int shelfId;

	public Book(int bookId, String bookIsbn, String bookTitle, String bookAuthor, String bookStatus) 
	{
		this.bookId = bookId;
		this.bookIsbn = bookIsbn;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookStatus = bookStatus;
	}
	
	public Book() {
		
	}

	public int getBookId()
	{
		return bookId;
	}

	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}

	public String getBookIsbn() 
	{
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn)
	{
		this.bookIsbn = bookIsbn;
	}

	public String getBookTitle()
	{
		return bookTitle;
	}

	public void setBookTitle(String bookTitle)
	{
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() 
	{
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor)
	{
		this.bookAuthor = bookAuthor;
	}

	public String getBookStatus() 
	{
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) 
	{
		this.bookStatus = bookStatus;
	}

	 
	
	public int getShelfId() {
		return shelfId;
	}

	public void setShelfId(int shelfId) {
		this.shelfId = shelfId;
	}

	@Override
	public String toString() 
	{
		return "Book [bookId=" + bookId + ", bookIsbn=" + bookIsbn + ", bookTitle=" + bookTitle + ", bookAuthor="
				+ bookAuthor + ", bookStatus=" + bookStatus + "]";
	}
	
	
}
