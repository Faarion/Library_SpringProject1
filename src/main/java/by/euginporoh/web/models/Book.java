package by.euginporoh.web.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
	
	@NotEmpty(message = "Название книги не должно быть пустым")
	@Size(min = 2, max = 50, message = "Название книги должно быть больше 2-ух и меньше 50-ти символов")
	private String name;
	
	@Size(min = 2, max = 50, message = "Имя авторы должно быть больше 2-ух и меньше 50-ти символов")
	private String Author;
	
	@Min(value = 1200, message = "Минимальный возраст - 6 лет")
	private int yearOfRelese;

	public Book(String name, String author, int yearOfRelese) {
		super();
		this.name = name;
		Author = author;
		this.yearOfRelese = yearOfRelese;
	}

	public Book() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getYearOfRelese() {
		return yearOfRelese;
	}

	public void setYearOfRelese(int yearOfRelese) {
		this.yearOfRelese = yearOfRelese;
	}
}
