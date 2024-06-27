package by.euginporoh.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.euginporoh.web.models.Person;

@Component
public class PersonDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
	}
	
	public Person show(int id) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[] {id}, 
				new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}
	
	public void save(Person person) {	
		jdbcTemplate.update("INSERT INTO person(fio, yearofbirth) values(?, ?)",
				person.getFio(), person.getYearOfBirth());
	}
	
	public void update(int id, Person updatePerson) {
		jdbcTemplate.update("UPDATE Person SET fio=?, yearOfBirth=? WHERE id=?", updatePerson.getFio(), updatePerson.getYearOfBirth(), id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
	}
}
