package com.mitrais.rms.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.rms.springboot.dao.BookRepository;
import com.mitrais.rms.springboot.model.Book;
import com.mitrais.rms.springboot.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookDao;
	
	@Override
	public List<Book> getAllBook()
	{
		return bookDao.findAll();
	}
	@Override
	public void deleteById(int id)
	{
		bookDao.deleteById(id);
	}

	@Override
	public void createBook(Book o) 
	{
		bookDao.save(o);
	}

	@Override
	public Optional<Book> getSelectedBookById(int id) 
	{
		return bookDao.findById(id);
	}
	
	@Override
	public List<Book> getSelectedBookByStatus(String status) {
		return bookDao.findByBookStatus(status);
	}
	@Override
	public List<Book> findByBookStatusAndBookTitle(String statusBook, String statusTitle) {
		return bookDao.findByBookStatusAndBookTitle(statusBook, statusTitle);
	}
}
