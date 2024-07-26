package by.euginporoh.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book implements Comparable<Book>{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Название книги не должно быть пустым")
	@Size(min = 2, max = 50, message = "Название книги должно быть больше 2-ух и меньше 50-ти символов")
	@Column(name = "book_name")
	private String name;
	
	@Size(min = 2, max = 50, message = "Имя авторы должно быть больше 2-ух и меньше 50-ти символов")
	@Column(name = "author")
	private String author;
	
	@Min(value = 1700, message = "Минимальный год - 1700 лет")
	@Column(name = "year_of_relese")
	private int yearOfRelese;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person owner;

	public Book(String name, String author, int yearOfRelese) {
		super();
		this.name = name;
		this.author = author;
		this.yearOfRelese = yearOfRelese;
	}

	public Book() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfRelese() {
		return yearOfRelese;
	}

	public void setYearOfRelese(int yearOfRelese) {
		this.yearOfRelese = yearOfRelese;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", yearOfRelese=" + yearOfRelese + "]";
	}

	@Override
	public int compareTo(Book o) {
		return this.yearOfRelese - o.getYearOfRelese();
	}
	
	
}
