package by.euginporoh.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.euginporoh.web.models.Book;

@Component
public class BookDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
	}
	
	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[] {id},
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
	}
	
	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO Book(author, yearOfRelese, name) values(?, ?, ?)",
				book.getAuthor(), book.getYearOfRelese(), book.getName());
	}
	
	public void update(int id, Book book) {
		jdbcTemplate.update("UPDATE Book SET name=?, author=?, yearOfRelese=? WHERE id=?", 
				book.getName(), book.getAuthor(), book.getYearOfRelese(), id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book Where id=?", id);
	}

}
