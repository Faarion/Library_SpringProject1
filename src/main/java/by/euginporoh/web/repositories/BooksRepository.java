package by.euginporoh.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import by.euginporoh.web.models.Book;

public interface BooksRepository extends JpaRepository<Book, Integer>{
	
}
