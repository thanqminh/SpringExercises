package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Book save(Book entity) {
		return bookRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Book entity) {
		bookRepository.delete(entity);
	}

}
	
