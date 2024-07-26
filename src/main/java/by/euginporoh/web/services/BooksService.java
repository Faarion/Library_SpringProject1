package by.euginporoh.web.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.euginporoh.web.models.Book;
import by.euginporoh.web.models.Person;
import by.euginporoh.web.repositories.BooksRepository;

@Service
@Transactional(readOnly = true)
public class BooksService {
	
	private final BooksRepository booksRepository;

	public BooksService(BooksRepository booksRepository) {
		super();
		this.booksRepository = booksRepository;
	}

	public List<Book> findAll(Optional<Integer> page, 
			Optional<Integer> books_per_page,
			Boolean sort_by_year) {
		if (sort_by_year == true) {
			if (page.isPresent() && books_per_page.isPresent()) {
				return booksRepository.findAll(PageRequest.of(page.get(), 
						books_per_page.get(), Sort.by("yearOfRelese"))).getContent();
			} else {
				List<Book> books = booksRepository.findAll();
				Collections.sort(books);
				return books;
			}
		} else {
			if (page.isPresent() && books_per_page.isPresent()) {
				return booksRepository.findAll(PageRequest.of(page.get(), books_per_page.get())).
						getContent();
			} else {
				return booksRepository.findAll();
			}
		}	
	}
	
	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	public Book findOne(int id) {
		Optional<Book> foundBook = booksRepository.findById(id);
		
		return foundBook.orElse(null);
	}
	
	@Transactional
	public void save(Book book) {
		booksRepository.save(book);
	}
	
	@Transactional
	public void update(int id, Book updateBook) {
		updateBook.setId(id);
		booksRepository.save(updateBook);
	}
	
	@Transactional
	public void delete(int id) {
		booksRepository.deleteById(id);
	}
	
	@Transactional
	public void asignPerson(int bookId, Person person) {
		Optional<Book> book = booksRepository.findById(bookId);
		if (book.isPresent()) {
			book.get().setOwner(person);
		}
	}
	
	@Transactional
	public void release(int bookId) {
		Optional<Book> book = booksRepository.findById(bookId);
		if (book.isPresent()) {
			book.get().setOwner(null);
		}
	}
}
