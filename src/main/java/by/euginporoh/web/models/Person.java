package by.euginporoh.web.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
	
	private int id;
	
	@Pattern(regexp = "[A-Za-zА-яЁё]+\\s[A-Za-zА-яЁё]+\\s[A-Za-zА-яЁё]+", 
			message = "Должно быть ФИО разделённое пробелами")
	@NotEmpty(message = "ФИО не должно быть пустым")
	@Size(min = 5, max = 60, message = "ФИО должно быть от 5 до 60 знаков")
	private String fio;
	
	@Min(value = 6, message = "Минимальный возраст - 6 лет")
	@Max(value = 130, message = "Максимальный возраст - 130 лет")
	private int yearOfBirth;

	public Person(int id, String fio, int age) {
		this.id = id;
		this.fio = fio;
		this.yearOfBirth = age;
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

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
}
