package com.lms.service;

import java.util.List;

import com.lms.entity.Book;

public interface BookService {
	
	Book saveBook(Book book);

	Book getBookById(Long id);

	List<Book> getAllBooks();

	void deleteBook(Long id);

}
