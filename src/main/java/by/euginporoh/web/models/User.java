package by.euginporoh.web.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	@Pattern(regexp = "\\w+\\s\\w+\\s\\w+", message = "ФИО разделённое пробелами")
	@NotEmpty(message = "ФИО не должно быть пустым")
	@Size(min = 5, max = 60, message = "ФИО должно быть от 5 до 60 знаков")
	private String fio;
	
	@Min(value = 6, message = "Минимальный возраст - 6 лет")
	@Max(value = 130, message = "Максимальный возраст - 130 лет")
	private int age;

	public User(String fio, int age) {
		this.fio = fio;
		this.age = age;
	}

	public User() {
	
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
