package by.euginporoh.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "[A-Za-zА-яЁё]+\\s[A-Za-zА-яЁё]+\\s[A-Za-zА-яЁё]+", 
			message = "Должно быть ФИО разделённое пробелами")
	@NotEmpty(message = "ФИО не должно быть пустым")
	@Size(min = 5, max = 60, message = "ФИО должно быть от 5 до 60 знаков")
	@Column(name = "fio")
	private String fio;
	
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "owner")
	private List<Book> books;

	
	public Person(String fio, Date dateOfBirth) {
		this.fio = fio;
		this.dateOfBirth = dateOfBirth;
	}

	public Person() {
	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int person_id) {
		this.id = person_id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String person_fio) {
		this.fio = person_fio;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fio=" + fio + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
